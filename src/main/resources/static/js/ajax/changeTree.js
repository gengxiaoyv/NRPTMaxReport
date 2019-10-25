var xmlHttp=new XMLHttpRequest();
xmlHttp.open("GET","changeTree?keyID=GM0101",true);
xmlHttp.send(null);


xmlHttp.onreadystatechange=function () {
    if (xmlHttp.readyState ==4 && xmlHttp.status ==200){
        console.log(xmlHttp);
    }
};