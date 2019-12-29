var loadItemsTimer = null;

var loadItems = function() {
    var itemsElem = document.getElementById('items');

    if (typeof AndroidObj === 'undefined' || typeof itemsElem === 'undefined') {
        console.log('No AndroidObj or no items.');
        return;
    }

    itemsElem.innerHTML = 'Loading...';

    var logs = JSON.parse(AndroidObj.getEventLogs());

    itemsElem.innerHTML = '';

    for (var i = 0; i < logs.length; i++) {
        var eventLog = logs[i];
        var boxElem = document.createElement('div');
        var dateElem = document.createElement('span');
        var msgElem = document.createElement('span');

        boxElem.classList.add("item");
        dateElem.classList.add("date");
        msgElem.classList.add("msg");

        dateElem.innerHTML = eventLog.time;
        msgElem.innerHTML = eventLog.log;

        boxElem.appendChild(dateElem);
        boxElem.appendChild(msgElem);

        itemsElem.appendChild(boxElem);
    }

    if (loadItemsTimer != null) {
        window.clearTimeout(loadItemsTimer);
    }

    loadItemsTimer = window.setTimeout(loadItems, 5000);
}

var deleteAll = function() {
    var itemsElem = document.getElementById('items');
    itemsElem.innerHTML = 'Deleting...'

    AndroidObj.deleteAll();
}

var openSettings = function() {
    window.location.href = 'settings.html';
}

loadItems();