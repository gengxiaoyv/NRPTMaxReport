//分行贷款情况


function branchBankChart(obj) {
    console.log(obj)
        var branchBankList = obj.dataList
        var branchBankListLength = branchBankList.length;            //记录拿到时数据的长度
        branchBankList = branchBankList.concat(branchBankList,branchBankList,branchBankList)       //将数据重复一遍
        var oUl = document.getElementsByTagName('ul')[0]              //获得ul节点

        var Domstr = ''
        
        branchBankList.forEach((item,index) => {  //根据数据生成dom结构的字符串
            Domstr = Domstr + `<li>
                                    <div class="store-num"><span class="storeWidth"></span> </div>
                                    <div class="city-name"><span class="locationName" style="font-weight:600">${item.name}</span> </div> 
                                    <div class="loan-num"><span class="loanWidth"></span> </div>
                                </li>`
        })

        oUl.innerHTML = Domstr

        //ulList
        var wrapper = document.getElementsByClassName('branchBank-list')[0]              //拿到外层盒子的高度
        var wrapperHeightStr = window.getComputedStyle(wrapper,null).height               //拿到外层盒子的高度
        var wrapperHeight = resolveHeightStr(wrapperHeightStr)                                //最外层盒子的高度
        var liHeight = wrapperHeight/12                                                  //计算12行数据，每行数据需要多高

        var cityName = document.getElementsByClassName('locationName')                  //拿到  中间城市的dom数组，需要居中处理

        var oUlHeightStr = window.getComputedStyle(oUl,null).height                     //拿到ul的高度                           //最外层盒子的高度
        var ulListHeight = resolveHeightStr(oUlHeightStr)                                //ul的高度


        var liList = document.getElementsByTagName('li')                               //拿到所有的li标签


        var storeWidth = document.getElementsByClassName('storeWidth')                 //获得所有存款横条的dom

        var loanWidth = document.getElementsByClassName('loanWidth')                 //获得所有贷款横条的dom
                                        
        for(var i = 0;i<liList.length;i++) {
            liList[i].style.height = liHeight + 'px'                                  //给每一个li标签设置高度
            liList[i].style.lineHeight = liHeight + 'px'                               // 给每一个li标签设施lineHeight
            cityName[i].style.lineHeight = liHeight + 'px'
            
            storeWidth[i].style.width = branchBankList[i].store/obj.storeMax * 100+ '%'    //计算横条的长度
            loanWidth[i].style.width = branchBankList[i].loan/obj.storeMax * 100+ '%'         //计算横条的长度
        }

        function resolveHeightStr(str) {    //处理   --->  '100px'的字符串
            var Arr = str.split('')            //处理字符串
            Arr.splice(Arr.length-2)  
            var height = Arr.join('')         //拿到dom的高度    210  
            return height
        }

        var topHeight = 0;
        var time;
        var maxTop = branchBankListLength*4* liHeight - wrapperHeight      //最多可以移动多少
           
        function move() {        //移动的函数    ----》 看不懂的话，那我也没办法，已经不知道怎么注释了
            clearTimeout(time)
            time = setTimeout(() => {
                    if(Math.abs(topHeight) >= maxTop) {
                        //回归原来的位置
                        topHeight = 0;
                        oUl.style.top = '0px'
                        move()
                    } else {
                        topHeight = topHeight - 1    //每次向上移动一个li的高度
                        oUl.style.top = topHeight + 'px'
                        move()
                    }
            },30)
        }
        move()
}
