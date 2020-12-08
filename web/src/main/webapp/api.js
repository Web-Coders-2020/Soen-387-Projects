
const urlPrefix="/"



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



function  createPost(title,content,authorizedGroup,successCallback,failureCallback)
{
  const payload = `
    {"title":"${title}"  , "content":"${content}" , "authorizedGroup":"${authorizedGroup}" }
  `
  let headers = {"content-type":"application/json"};

  let responseCallback = function (){
    if (this.readyState === 4) {

      if(this.status === 201)
      {
        if(successCallback!=null)
          successCallback(this.responseText)
      }else if(failureCallback!=null)
        failureCallback(`unknown server error ${this.responseText}`)
    }
  };

  callHttpRequest("posts","POST",responseCallback,{},true,headers,payload)
}



function  updatePost(postId,title,content,authorizedGroup,successCallback,failureCallback)
{
  const payload = `
    {"postId":"${postId}", "title":"${title}"  , "content":"${content}" , "authorizedGroup":"${authorizedGroup}" }
  `
  let headers = {"content-type":"application/json"};

  let responseCallback = function (){
    if (this.readyState === 4) {

      if(this.status === 202)
      {
        if(successCallback!=null)
          successCallback(this.responseText)
      }else if(failureCallback!=null)
        failureCallback(`unknown server error ${this.responseText}`)
    }
  };

  callHttpRequest("posts","PUT",responseCallback,{},true,headers,payload)
}



function  fetchPosts(creatorId,startDate,endDate,hashTags,successCallback,failureCallback)
{

  let headers = {"content-type":"application/json"};

  let responseCallback = function (){
    if (this.readyState === 4) {

      if(this.status === 200)
      {
        if(successCallback!=null)
          successCallback(this.responseText)
      }else if(failureCallback!=null)
        failureCallback(`unknown server error ${this.responseText}`)
    }
  };

  let queryStrings = {
    "creatorId":creatorId,
    "startDate":startDate,
    "endDate":endDate,
    "hashTags":hashTags
  };

  callHttpRequest("posts","GET",responseCallback,queryStrings,true,headers)
}


function  deletePost(postId,successCallback,failureCallback)
{


  let responseCallback = function (){
    if (this.readyState === 4) {

      if(this.status === 202)
      {
        if(successCallback!=null)
          successCallback()
      }else if(failureCallback!=null)
        failureCallback(`unknown server error ${this.responseText}`)
    }
  };

  let queryStrings = {
    "postId":postId,
  };

  callHttpRequest("posts","DELETE",responseCallback,queryStrings,true)
}



function redirectToPage(url) {
  window.location.replace(url);
}

function callLogin(userName,password)
{
  login(userName,password,function () {
    redirectToPage("/posts.jsp");
  },function (reason) {
    alert(`failed, reason:${reason}`)
  })
}

function callCreatePostAndRedirectToMainPageIfSuccessful(title,content,authorizedGroup)
{
  createPost(title,content,authorizedGroup,function(postId){
    alert(`created successfully :${postId}`)
    redirectToPage("/posts.jsp")
  },function (reason){
    alert(`failed, reason:${reason}`)
  })
}

function appendBreakLineTo(parent) {
  return parent.appendChild(document.createElement("br"));
}

function callFetchPostsAndAppendResultToPage(creatorId,startDate,endDate,hashTags)
{
  fetchPosts(creatorId,startDate,endDate,hashTags,function (result){


    var division = document.getElementById("postsDiv");


    let jsonArray = JSON.parse(result);
    for(i = 0 ; i < jsonArray.length;i++)
    {

      const post = jsonArray[i];

      var Creator = document.createTextNode(`Creator : ${post.creatorFullName}`)
      var Title = document.createTextNode(`Title : ${post.title}`)
      var Content = document.createTextNode(`Content : ${post.content}`)
      var Creation = document.createTextNode(`Creation Date : ${post.creationDate}`)

      var deleteButton = document.createElement("button");
      deleteButton.innerHTML =  "Delete"

      deleteButton.addEventListener ("click", function() {
        deletePost(post.postId,function () {
          alert("was deleted successfully")
          redirectToPage("/postslist.jsp")
        },function (reason) {
          alert(`something went wrong , ${reason}`)
        })
      });

      var updateButton = document.createElement("button");
      updateButton.innerHTML =  "Update"
      updateButton.addEventListener ("click", function() {
        redirectToPage(`/update-post?postId=${post.postId}`)
      });


      appendBreakLineTo(division)
      appendBreakLineTo(division)
      division.appendChild(Creator)
      appendBreakLineTo(division)
      division.appendChild(Title)
      appendBreakLineTo(division)
      division.appendChild(Content)
      appendBreakLineTo(division)
      division.appendChild(Creation)
      appendBreakLineTo(division)
      division.appendChild(deleteButton)
      division.appendChild(updateButton)
      appendBreakLineTo(division)
      appendBreakLineTo(division)

    }



  },function(reason){ alert(`failed, reason:${reason}`)})
}


function callUpdatePostAndRedirectToMainPageIfSuccessful(postId,title,content,authorizedGroup)
{
  updatePost(postId,title,content,authorizedGroup,function(){
    alert(`updated successfully `)
    redirectToPage("/posts.jsp")
  },function (reason){
    alert(`failed, reason:${reason}`)
  })
}