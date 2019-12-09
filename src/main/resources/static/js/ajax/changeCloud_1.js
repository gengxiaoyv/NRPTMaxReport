var xmlHttp=new XMLHttpRequest();
xmlHttp.open("GET","changeCloud_1?keyID=GM010204",true);
xmlHttp.send(null);


xmlHttp.onreadystatechange=function () {
    if (xmlHttp.readyState ==4 && xmlHttp.status ==200){
        console.log(xmlHttp);
    }
};