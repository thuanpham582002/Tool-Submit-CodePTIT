function init() {
    chrome.storage.local.set({
        'urls': ['https://code.ptit.edu.vn/student/history']
    }, function() {});
	chrome.storage.local.get('urls', function (result) {
		urls = result.urls;
	});

}


function openOrFocusOptionsPage() {

	var optionsUrl = chrome.extension.getURL('options.html');

	chrome.tabs.query({}, function (extensionTabs) {

		var found = false;

		for (var i = 0; i < extensionTabs.length; i++) {

			if (optionsUrl == extensionTabs[i].url) {
				found = true;
				console.log("tab id: " + extensionTabs[i].id);
				chrome.tabs.update(extensionTabs[i].id, { "selected": true });
			}

		}

		if (found == false) {
			chrome.tabs.create({ url: "options.html" });
		}

	});
}

var callback = function (info) {
	var denyRequest = false;

	if (!urls) {
		return;
	}

	for (var i = 0; i < urls.length; i++) {

		var regex = new RegExp(urls[i]);
		var res = info.url.match(regex);

		if (res != null && res[0] != null) {
			denyRequest = true;
			//console.log("URL: " + info.url + " blocked by: " + urls[i]);
			break;
		}

	}

	return { cancel: denyRequest };
};

var filter = { urls: ["<all_urls>"], };
var addInfo = ["blocking"];

var urls;
init();

chrome.webRequest.onBeforeRequest.addListener(callback, filter, addInfo);

chrome.storage.onChanged.addListener(function (changes, areaName) {
	urls = changes.urls.newValue;
});

chrome.browserAction.onClicked.addListener(function (tab) { openOrFocusOptionsPage() });
