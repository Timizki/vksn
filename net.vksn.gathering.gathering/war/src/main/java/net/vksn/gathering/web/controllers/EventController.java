package net.vksn.gathering.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Role;
import net.vksn.bedrock.model.User;
import net.vksn.bedrock.services.UserService;
import net.vksn.gathering.model.Event;
import net.vksn.gathering.model.Instance;
import net.vksn.gathering.model.Participant;
import net.vksn.gathering.query.EventQuery;
import net.vksn.gathering.services.EventService;
import net.vksn.gathering.services.InstanceService;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

//This is bit large class need more refactoring, problems maybe in architecture
public class EventController extends MultiActionController {
	private static final Logger log = Logger.getLogger(EventController.class);
	private EventService eventService;
	private UserService userService;
	private InstanceService instanceService;
	private Map<String, String> viewNames;
	
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setInstanceService(InstanceService instanceService) {
		this.instanceService = instanceService;
	}

	public void setViewNames(Map<String, String> viewNames) {
		this.viewNames = viewNames;
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(viewNames.get("view"));
		String eventId = request.getParameter("eventId");
		int id = 0;
		if (eventId != null) {
			try {
				id = Integer.valueOf(eventId);
			} catch (NumberFormatException e) {
				return browse(request, response);
			}
		}
		Event event;
		try {
			event = eventService.get(id);
		} catch (EntityNotFoundException e) {
			return browse(request, response);
		}
		mav.addObject("event", event);
		return mav;
	}
	
	//TODO: Move this logic to service layer in version 2.0
	public ModelAndView deleteInstance(HttpServletRequest request, HttpServletResponse response) {
		String instanceIdParam = request.getParameter("instanceId");
		String eventIdParam = request.getParameter("eventId");
		try {
			Event event = this.eventService.get(Integer.valueOf(eventIdParam));
			Instance instance = this.instanceService.get(Integer.valueOf(instanceIdParam));
			event.getInstances().remove(instance);
			this.eventService.store(event);
			this.instanceService.remove(Integer.valueOf(instanceIdParam));
		}
		catch(EntityNotFoundException e) {
			//TODO: This is something horrible please fix me!!
		}
		return new ModelAndView(viewNames.get("redirectToView")+request.getParameter("eventId"));
	}
	
	private ModelAndView redirectToView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		//Trying redirect so browser's urls stays the same
		try {
			response.sendRedirect("/view.do");
		} catch (IOException e) {
			//If redirect fails for some reason user will be send to same view but with wrong URL :(
			mav = view(request, response);
		}
		return mav;
	}
	//TODO: Clean this to be more elegant; binders and validators etc.
	// Maybe this should be in own controller
	public ModelAndView addParticipant(HttpServletRequest request,
			HttpServletResponse response) {
		String instanceId = request.getParameter("instanceId");
		String participantName = request.getParameter("name");
		if (participantName != null) {
			Integer id = null;
			Participant participant = new Participant();
			try {
				id = Integer.valueOf(instanceId);
			} catch (NumberFormatException e) {
				// TODO: Add some logging here or error handling
			}
			participant.setName(participantName);
			try {
				Instance instance = this.instanceService.get(id);			
				this.instanceService.addParticipant(instance, participant);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return redirectToView(request, response);
	}

	public ModelAndView removeParticipant(HttpServletRequest request,
			HttpServletResponse response) {
		String instanceIdParam = request.getParameter("instanceId");
		String participantIdParam = request.getParameter("participantId");
		Integer instanceId = null;
		Integer participantId = null;		
		if(instanceIdParam != null && participantIdParam != null) {
			try {
				instanceId = Integer.valueOf(instanceIdParam);
				participantId = Integer.valueOf(participantIdParam);
			}
			catch(NumberFormatException e) {
				//TODO: Error logging
			}
			Instance instance;
			try {
				instance = this.instanceService.get(instanceId);
			} catch (EntityNotFoundException e) {
					e.printStackTrace();
					instance = null;
			}
			for(Participant participant : instance.getParticipants()) {
				if(participantId.equals(participant.getId())) {
					instance.getParticipants().remove(participant);
					break;
				}
			}
			try {
				this.instanceService.store(instance);
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
					
			}
		}
		return redirectToView(request, response);
	}

	public ModelAndView addInstance(HttpServletRequest request,
			HttpServletResponse response) {
		return view(request, response);
	}

	public ModelAndView removeInstance(HttpServletRequest request,
			HttpServletResponse response) {
		return viewUsersEvent(request, response);
	}

	public ModelAndView browse(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(viewNames.get("browse"));
		EventQuery query = new EventQuery();
		return populateMavForBrowseEvents(mav, query);
	}

	//What about ROLE_ADMIN there should not be any events for it? 
	// TODO: resolve all events when user have role ROLE_ADMIN
	public ModelAndView browseUserEvents(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(viewNames.get("browse"));
		User user = getUser(request);
		EventQuery query = new EventQuery();
		query.setUser(user);
		return populateMavForBrowseEvents(mav, query);
	}
	
	private ModelAndView populateMavForBrowseEvents(ModelAndView mav, EventQuery query) {
		Collection<Event> events = eventService.getByQuery(query);
		mav.addObject("events", events);
		return mav;
	}
	
	public ModelAndView viewUsersEvent(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		EventQuery query = new EventQuery();
		User user = getUser(request);
		String eventIdParam = request.getParameter("eventId");
		Integer eventId = null;
		if(eventIdParam != null) {
			try {
				eventId = Integer.valueOf(eventIdParam);
			}
			catch(NumberFormatException e) {
				eventId = null;
			}
		}
	
		for(Role role : user.getRoles()) {
			if("ROLE_ADMIN".equals(role.getAuthority())) {
				return browse(request, response);
			}
		}
		query.setUser(user);
		if(eventId != null) {
			query.setId(eventId);
		}
		List<Event> events = new ArrayList<Event>();
		events = (List<Event>) this.eventService.getByQuery(query);
		if (events.size() == 1) {
			mav.addObject("event", events.iterator().next());
			mav.setViewName(viewNames.get("viewUsersEvent"));

		} 
		else{
			mav = browseUserEvents(request, response);
		}

		return mav;
	}
	
	private User getUser(HttpServletRequest request) {
		
		try {
			return this.userService.getUserByUsername(request.getRemoteUser());
		} catch (EntityNotFoundException e) {
			return null;
		}
	}
}