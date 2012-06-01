Ext.onReady(function(){
    var win;
    var button = Ext.get('nappi');

    button.on('click', function(){
        // create the window on the first click and reuse on subsequent clicks
        if(!win){
            win = new Ext.Window({
                applyTo     : 'hello-win',
                layout      : 'fit',
                width       : 500,
                height      : 300,
                closeAction :'hide',
                plain       : true,
                items       : new Ext.TabPanel({
                    applyTo        : 'hello-tabs',
                    autoTabs       : true,
                    activeTab      : 0,
                    deferredRender : false,
                    border         : false
                })
            });
        }
        win.show(button);
    });
});