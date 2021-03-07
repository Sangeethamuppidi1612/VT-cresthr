import "whatwg-fetch";

class ApiClient {

  setAccessToken(accessToken) {
    this.accessToken = accessToken;
  }

  async get(url) {
    const fullUrl = ApiClient.createUrl(url);
    return fetch(fullUrl)
        .then(ApiClient.checkStatus)
        .then((response) => response.json())
        .catch((e) => {console.log("error", e)});
  }

  async post(url, data, state) {
    const fullUrl = ApiClient.createUrl(url);
    return fetch(fullUrl, {
          method : "POST",
          body : JSON.stringify(data),
          headers : { 'Content-Type': 'application/json' },
        })
        .then(ApiClient.checkStatus)
        .then((response) => response.json())
        .catch((e) => {console.log("error", e)});
  }

  async put(url, data, state) {
    const fullUrl = ApiClient.createUrl(url);
    return fetch(fullUrl, {
          method : "PUT",
          body : JSON.stringify(data),
          headers : { 'Content-Type': 'application/json' },
        })
        .then(ApiClient.checkStatus)
        .then((response) => response.json())
        .catch((e) => {console.log("error", e)});
  }

  static createUrl(url) {
    return `/api${url}`;
  }

  static checkStatus(response) {
    if (response.status >= 200 && response.status < 300) {
      return response;
    } else {
      const error = new Error(response.statusText);
      response.text().then(function (text) {
        console.log("error text", text);
      	alert("Error Status :" + response.status + " :: " +text);
		})
      error.response = response;
      throw error;
    }
  }
}

export const Api = window.apiClient = new ApiClient();