var configUrl = {
    "urlLocation":"http://172.30.117.246:8080/",
     // "urlRelease":"http://172.28.4.15:8088/"
    "urlRelease":"http://127.0.0.1:8088/"
};
//设置cookie
function addCookie (name, value, days,path) {
    var name = escape(name);
    var value = escape(value);
    var expires = new Date();
    expires.setTime(expires.getTime() + days * 3600000 * 24);
    path = path == "" ? "" : ";path=" + path;
    var _expires = (typeof days) == "string"?"":";expires=" + expires.toUTCString();
    document.cookie = name + "=" +value +_expires + path;
}
//获取cookie的值，根据cookie的键获取值
function getCookieValue (name) {
//    用处理字符串的港式查找到key对应的value
    var name = escape(name);
//    读取cookie属性吗、，这将返回文档的所有cookie
    var allCookies = document.cookie;
//    查找名为name的cookie的开始位置
    name += "=";
    var pos = allCookies.indexOf(name);
//    如果找到了具有该名字的cookie，那么提取并使用它的值
    if (pos != -1) {
        var start = pos + name.length;
        var end = allCookies.indexOf(";",start);
        if (end == -1) end = allCookies.length;
        var value = allCookies.substring(start, end);//提取cookie的值
        return (value);
    }else {
        return "";
    }
}
function deleteCookie (name,path) {
    var name = escape(name);
    var expires = new Date(0);
    path = path == "" ? "" : ";path=" + path;
    document.cookie = name + "=" + ";expires=" + expires.toUTCString() + path;
}
function toggleSidebar() {
    var oAuth = getCookieValue("oAuthority");
    var oSidebarTitle = document.getElementById("toggleSidebar");
    var oSidebar = document.getElementById("sidebar");
    if(oAuth > 1 && oSidebarTitle && oSidebar){
        oSidebar.parentNode.removeChild(oSidebar);
        oSidebarTitle.parentNode.removeChild(oSidebarTitle);
    }else if(oSidebarTitle){
        oSidebarTitle.innerText = "显示隐藏侧边导航栏";
    }
}

