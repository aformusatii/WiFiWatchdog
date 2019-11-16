var openHome = function() {
    document.location.href = 'index.html';
}

var loadSettings = function() {
    if (typeof AndroidObj === 'undefined') {
        console.log('No AndroidObj or no items.');
        return;
    }

    document.getElementById('check_url').value = AndroidObj.getSettingsUrl();
    document.getElementById('check_period').value = AndroidObj.getSettingsCheckInterval();
    document.getElementById('check_download_timeout').value = AndroidObj.getSettingsDownloadTimeout();
    document.getElementById('check_connection_timeout').value = AndroidObj.getSettingsConnectionTimeout();
    document.getElementById('check_read_timeout').value = AndroidObj.getSettingsReadTimeout();
}

loadSettings();

var saveSettings = function() {
    if (typeof AndroidObj === 'undefined') {
        console.log('No AndroidObj or no items.');
        return;
    }

    AndroidObj.setSettingsUrl(document.getElementById('check_url').value);
    AndroidObj.setSettingsCheckInterval(parseInt(document.getElementById('check_period').value));
    AndroidObj.setSettingsDownloadTimeout(parseInt(document.getElementById('check_download_timeout').value));
    AndroidObj.setSettingsConnectionTimeout(parseInt(document.getElementById('check_connection_timeout').value));
    AndroidObj.setSettingsReadTimeout(parseInt(document.getElementById('check_read_timeout').value));
}