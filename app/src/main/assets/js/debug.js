(function() {

    if (window.debug_screen_info_added) {
        return;
    }

    window.debug_screen_info_added = true;

    var style = document.createElement('style');
    style.type = 'text/css';
    style.innerHTML = '#debug_screen_info {' +
        'color: #FFFFFF;' +
        'position: fixed;' +
        'bottom: 10px;' +
        'right: 10px;' +
        'padding: 5px;' +
        'background-color: #000000;' +
        'font-size: 14px;' +
    '}';

    document.getElementsByTagName('head')[0].appendChild(style);

    var elem = document.createElement('div');
    elem.id = 'debug_screen_info'
    document.body.appendChild(elem);

    var resizeScreen = function() {
        var w = window.innerWidth
        || document.documentElement.clientWidth
        || document.body.clientWidth;

        var h = window.innerHeight
        || document.documentElement.clientHeight
        || document.body.clientHeight;


        document.getElementById('debug_screen_info').innerHTML = w + 'x' + h;
    }

    window.addEventListener('resize', resizeScreen);

    resizeScreen();
})();