
const urlPrefix="/web/"



function formatParams( params ){

  if(params.size === 0)
    return ""

  return "?" + Object
      .keys(params)
      .map(function(key){
        return key+"="+encodeURIComponent(params[key])
      })
      .join("&")
}

function callHttpRequest(resource,method ,responseCallback,queryStrings ={},async=true,headers = {},payload,errorCallback )
{
  const http = new XMLHttpRequest();
  http.onreadystatechange = responseCallback

  http.onerror = function(){
    if(errorCallback!=null)
      errorCallback()
  }

  http.ontimeout = function(){
    if(errorCallback!=null)
      errorCallback()
  }


  http.open(method, urlPrefix + resource + formatParams(queryStrings), async);


  Object.keys(headers).forEach(function(key){
    http.setRequestHeader(key,headers[key])
  });

  http.send(payload);
}


/**
 * call this function like :
 *
 *  login('1','hash1' , function(){alert("Yeay")  , function(reason){alert("failed due to ${reason}")} }
 */
function login(userName,password,successCallback,failureCallback)
{

  let responseCallback = function (){
    if (this.readyState === 4) {

      if(this.status === 200)
        if(successCallback!=null)
          successCallback()

      if(this.status === 401)
        if(failureCallback!=null)
          failureCallback("unauthorized")

      if(this.status !== 401 && this.status !==200)
        if(failureCallback!=null)
          failureCallback(`unknown server error ${this.responseText}`)
    }
  };

  let errorCallback = function () {
    if(failureCallback!=null)
      failureCallback(`network error`)
  };


  callHttpRequest("login","POST",responseCallback, {
    userId: userName,
    password: password,
  },true,{},null,errorCallback)
}



function  createPost(title,content,authorizedGroup)
{
  const payload = `
    {"title":"${title}"  , "content":"${content}" , "authorizedGroup":"${authorizedGroup}" }
  `
  let headers = {"content-type":"application/json"};

  let responseCallback = function (){
    alert(this.status);
    alert(this.responseText);
  };

  callHttpRequest("posts","POST",responseCallback,{},true,headers,payload)
}


function  fetchPosts()
{
  const payload = `
    {"title":"${title}"  , "content":"${content}" , "authorizedGroup":"${authorizedGroup}" }
  `
  let headers = {"content-type":"application/json"};

  let responseCallback = function (){
    if(this.readyState === 4)
      if(this.status === 200)
      {
        var paragraph = document.getElementById("p");
        var text = document.createTextNode(`${this.responseText}`);
        paragraph.appendChild(text);
      }

  };

  callHttpRequest("posts","GET",responseCallback,{},true,headers,payload)
}


function callLogin(userName,password)
{
  login(userName,password,function () {
    alert("logged in")
  },function (reason) {
    alert(`failed, reason:${reason}`)
  })
}