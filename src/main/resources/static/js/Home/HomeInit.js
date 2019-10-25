!function () {
    let oDiv = document.getElementsByClassName('wrapper')[0];
    let url = 'getEntranceListData'
    //let data = [{"name":"浦发实时大屏","url":"www.baidu.com"},{"name":"浦发实时大屏","url":"www.baidu.com"},{"name":"浦发实时大屏","url":"www.baidu.com"},{"name":"浦发实时大屏","url":"www.baidu.com"},{"name":"浦发实时大屏","url":"www.baidu.com"},{"name":"浦发实时大屏","url":"www.baidu.com"},{"name":"浦发实时大屏","url":"www.baidu.com"},{"name":"浦发实时大屏","url":"www.baidu.com"},{"name":"浦发实时大屏","url":"www.baidu.com"}]
    $.ajax({ url: url,success: function( ret){
      console.log(ret)
      rendererDom (ret)
      }});
      function rendererDom ( data) {
        let str = ''
        data.forEach(element => {
          console.log(element)
          str += `
          <a href="${element.url}" class="flex-item" " target="_blank">
          <div class="name ">${element.name}</div>
      </a>
          `
        });
        oDiv.innerHTML = str
      }
} ()