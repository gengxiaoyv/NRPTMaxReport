var vm = new Vue({
  el: '#app',
  data: {
    main01: {
      type: '',
      yData: [],
      dataLine: [],
      dataWidth: [],
      textLeft: [],
      id: []
    },
    main02: {
      type: '',
      yData: [],
      dataLine: [],
      dataWidth: [],
      textLeft: [],
      id: []
    },
    main03: {
      type: '',
      yData: [],
      dataLine: [],
      dataWidth: [],
      textLeft: [],
      id: []
    },
    main04: {
      type: '',
      yData: [''],
      dataLine: [],
      dataWidth: [],
      textLeft: [],
      id: []
    },
    pie11: {
      pieData: ''
    },
    pie12: {
      pieData: ''
    },
    pie13: {
      pieData: ''
    },
    pie14: {
      pieData: ''
    },
    pie15: {
      pieData: ''
    },
    pie16: {
      pieData: ''
    },
    pie17: {
      pieData: ''
    },
    pie18: {
      pieData: ''
    },
    pie19: {
      pieData: ''
    },
    pie21: {
      pieData: ''
    },
    pie22: {
      pieData: ''
    },
    pie23: {
      pieData: ''
    },
    pie24: {
      pieData: ''
    },
    pie25: {
      pieData: ''
    },
    pie25: {
      pieData: ''
    },
    pie26: {
      pieData: ''
    },
    pie27: {
      pieData: ''
    },
    pie28: {
      pieData: ''
    },
    pie29: {
      pieData: ''
    },
    pie31: {
      pieData: ''
    },
    pie32: {
      pieData: ''
    },
    pie33: {
      pieData: ''
    },
    pie34: {
      pieData: ''
    },
    pie35: {
      pieData: ''
    },
    pie36: {
      pieData: ''
    },
    pie37: {
      pieData: ''
    },
    pie38: {
      pieData: ''
    },
    pie39: {
      pieData: ''
    },
    pie41: {
      pieData: ''
    },
    pie42: {
      pieData: ''
    },
    pie43: {
      pieData: ''
    },
    pie44: {
      pieData: ''
    },
    pie45: {
      pieData: ''
    },
    pie46: {
      pieData: ''
    },
    pie47: {
      pieData: ''
    },
    pie48: {
      pieData: ''
    },
    pie49: {
      pieData: ''
    },
    time: "",
//    flagg: false,
    imgsrc: ['css/sasGame/img/0.png', 'css/sasGame/img/1.png', 'css/sasGame/img/2.png', 'css/sasGame/img/3.png', 'css/sasGame/img/4.png', 'css/sasGame/img/5.png', 'css/sasGame/img/6.png', 'css/sasGame/img/7.png', 'css/sasGame/img/8.png', 'css/sasGame/img/9.png'],
    timeObj: { //时间相关
      sOne: 0, //秒个位
      sTwo: 0, //秒十位
      mOne: 0, //分个位
      mTwo: 0, //分十位
      hOne: 4, //时个位
      hTwo: 2, //时十位
    },
    logData: [], // 日志数据
    startShow: true, // 开始按钮显示
    countDownTime: 86400000,
    shadowStyle1: {
      active: false,
    },
    shadowStyle2: {
      active: false,
    },
    shadowStyle3: {
      active: false,
    },
    shadowStyle4: {
      active: false,
    },
    offsetTopNumber: 0,
    offsetTopFlag: true

  },
  methods: {
	  
    // 计算时间
    countDown: function (time) {
      var date2
      var date1
      var self = this
      console.log(self)
      
      window.requestAnimationFrame(step)

      function step() {
        date1 = new Date().getTime(); //开始时间
        if (date2 == undefined) {
          date2 = new Date().getTime() + time; //结束时间
        }

        var date3 = date2 - date1; //时间差的毫秒数      
        //计算出相差天数
        var days = Math.floor(date3 / (24 * 3600 * 1000))
        //计算出小时数
        var leave1 = date3 % (24 * 3600 * 1000) //计算天数后剩余的毫秒数
        var hours = self.zeroFill(Math.floor(leave1 / (3600 * 1000)))
        //计算相差分钟数
        var leave2 = leave1 % (3600 * 1000) //计算小时数后剩余的毫秒数
        var minutes = self.zeroFill(Math.floor(leave2 / (60 * 1000)))
        //计算相差秒数
        var leave3 = leave2 % (60 * 1000) //计算分钟数后剩余的毫秒数
        var seconds = self.zeroFill(Math.round(leave3 / 1000))
        let temptime = self.time = days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒"
        if (temptime == '1天00小时00分钟00秒') {
          //self.time = '24小时00分钟00秒'
        	self.time = '00小时00分钟10秒'
        } else {
          self.time = temptime.substring(temptime.length - 11, temptime.length)
        }
       
        if (self.time.substring(9, 10) == 0 && self.time.substring(8, 9) == 6) {
          self.timeObj.sOne = 9
          self.timeObj.sTwo = 5
        } else {
          self.timeObj.sOne = self.time.substring(9, 10)
          self.timeObj.sTwo = self.time.substring(8, 9)
        }
        self.timeObj.mOne = self.time.substring(5, 6)
        self.timeObj.mTwo = self.time.substring(4, 5)
        self.timeObj.hOne = self.time.substring(1, 2)
        self.timeObj.hTwo = self.time.substring(0, 1)
        if (self.time == '00小时00分钟00秒') {
          console.log('时间到')
          return
        } else {
          window.requestAnimationFrame(step)
        }
      }
    },
    zeroFill: function (num) {
      num = '' + num
      if (num.length == 1) {
        num = '0' + num
      }
      return num
    },
    //处理echarts Bar
    drawBar(id, yData, dataLine) {
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        grid: [{
          left: '10px',
          top: '12%',
          right: '10px',
          bottom: '0%',
          containLabel: true
        }],
        xAxis: [{
          show: false
        }],
        yAxis: [{
            axisTick: 'none',
            axisLine: 'none',
            inverse: true,
            axisLabel: {
              textStyle: {
                // color: 'rgb(105, 171, 205)',
                color: "#fff",
                fontSize: '16'
              }
            },
            data: yData
          },
          {

            axisLine: {
              lineStyle: {
                color: 'rgba(0,0,0,0)'
              }
            },
            data: []
          }
        ],
        series: [{
            name: '条',
            type: 'bar',
            stack: 'b',
            yAxisIndex: 0,
            data: dataLine,
            label: {
              normal: {
                show: true,
                position: 'right',
                textStyle: {
                  // color: 'rgb(105, 171, 205)',
                  color: "#fff",
                  fontSize: '16'
                }
              }
            },
            barWidth: 10,
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 1, 0,
                  [{
                      offset: 0,
                      color: 'rgba(27,68,158,1)'
                    },
                    {
                      offset: 1,
                      color: 'rgba(104,165,228,1)'
                    }
                  ]
                )
              }
            },
            z: 2
          },
          {
            name: '外框',
            type: 'bar',
            barGap: '-100%',
            data: [520, 520, 520, 520, 520],
            color: 'rgba(0,0,0,0)',
            z: 0
          },

        ]
      })
    },
    forId(num, index) {
      return "pie" + num + (index + 1)
    },
    //处理echarts Pie
    drawPie(id, pieData) {
      if (document.getElementById(id) == null) {
        return
      }
      echarts.dispose(document.getElementById(id))
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        title: {
          text: pieData + '%',
          left: '45%',
          top: '0%',
          textStyle: {
            fontWeight: 'normal',
            color: 'rgb(255, 255, 255)',
            fontSize: '16',
            fontWeight: 600
          }
        },
        color: ['rgba(232, 49, 35, 1)'],
        series: [{
          name: 'Line 1',
          type: 'pie',
          clockWise: true,
          center: ['30%', '50%'],
          radius: ['40%', '70%'],
          itemStyle: {
            normal: {
              label: {
                show: false
              },
              labelLine: {
                show: false
              }
            }
          },
          hoverAnimation: false,
          data: [{
            value: pieData,
            name: '01',
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 1, 0,
                  [{
                      offset: 0,
                      color: 'rgba(27,68,158,1)'
                    },
                    {
                      offset: 1,
                      color: 'rgba(104,165,228,1)'
                    }
                  ]
                ),
                label: {
                  show: false
                },
                labelLine: {
                  show: false
                }
              }
            }
          }, {
            name: '02',
            value: 100 - pieData
          }]
        }]
      })
    },
    // 处理接收数据
    processData(data) {
      let rankData = data.rankData
      // console.log(rankData)
      rankData.forEach((element, index) => {
        this[['main0' + (index + 1)]].type = element.type;
        let data = element.data
        let teamName = [] //队名
        let score = [] //对得分
        let percent = [] // 宽度百分比
        let textLeft = [] // 文字左侧距离
        let id = []
        this.sortData(data)
        data.forEach((ele, ind) => {
          this[['pie' + (index + 1) + (ind + 1)]].pieData = ele.progress;
          teamName.push(ele.teamName)
          score.push(ele.score)
          percent.push({
            width: ele.percent * 4 / 10 + "%"
          })
          textLeft.push({
            left: 31 + ele.percent*4 / 10 + "%"
          })
          id.push(ele.id)
        });
        this[['main0' + (index + 1)]].yData = teamName;
        this[['main0' + (index + 1)]].dataLine = score;
        // console.log(percent)
        // console.log(this[['main0' + (index + 1)]].dataWidth)
        // if (JSON.stringify(this[['main0' + (index + 1)]].dataWidth) != JSON.stringify(percent)) {
        //   this['shadowStyle' + (index + 1)].active = true
        //   setTimeout(() => {
        //     this['shadowStyle' + (index + 1)].active = false
        //   }, 1000)
        // }
        this[['main0' + (index + 1)]].dataWidth = percent;
        this[['main0' + (index + 1)]].textLeft = textLeft;
        this[['main0' + (index + 1)]].id = id;
      });

      let logData = data.logData
      if (JSON.stringify(this.logData) !==JSON.stringify(logData) ) {
        this.logData = logData
         this.bf()
      }
      this.logData = logData
      var cData = new Date().getTime();

      // setInterval(() => {
      //   cData = new Date().getTime();
      //   this.logData.splice(0, 0, {
      //     "teamID": "555",
      //     "teamName": "测试",
      //     "teamChange": "-23",
      //     "updateTime": cData,
      //     "teamRank": "2",
      //   })
      //   this.bf()
      // }, 3000)

    },
    //绑定开始按钮事件
    bindStart() {
      //   this.countDown(this.countDownTime) //时间初始化 24小时
      //   this.startShow = !this.startShow

    },
    //处理音频播放器
    bf() {
      var audio = document.getElementById('music1');
      var timer
      clearTimeout(timer)
      if (audio !== null) {
        //检测播放是否已暂停.audio.paused 在播放器播放时返回false.
        if (audio.paused) {
          audio.play(); //audio.play();// 这个就是播放  
          timer = setTimeout(() => {
            audio.pause(); // 这个就是暂停
          }, 999)
        } else {
          audio.pause(); // 这个就是暂停
        }
      }
    },

    // 排序
    sortData(data) {
      data.sort((a, b) => {
        return b.score - a.score
      })
      return data
    }
  },
  computed: {

  },
  watch: {

    //监听echart Pie数据变化
    'pie11': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie11', this.pie11.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie12': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie12', this.pie12.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie13': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie13', this.pie13.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie14': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie14', this.pie14.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie15': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie15', this.pie15.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie16': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie16', this.pie16.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie17': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie17', this.pie17.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie18': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie18', this.pie18.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie19': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie19', this.pie19.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie21': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie21', this.pie21.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie22': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie22', this.pie22.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie23': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie23', this.pie23.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie24': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie24', this.pie24.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie25': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie25', this.pie25.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie26': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie26', this.pie26.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie27': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie27', this.pie27.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie28': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie28', this.pie28.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie29': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie29', this.pie29.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie31': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie31', this.pie31.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie32': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie32', this.pie32.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie33': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie33', this.pie33.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie34': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie34', this.pie34.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie35': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie35', this.pie35.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie36': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie36', this.pie36.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie37': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie37', this.pie37.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie38': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie38', this.pie38.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie39': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie39', this.pie39.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie41': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie41', this.pie41.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie42': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie42', this.pie42.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie43': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie43', this.pie43.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie44': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie44', this.pie44.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie45': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie45', this.pie45.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie46': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie46', this.pie46.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie47': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie47', this.pie47.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie48': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie48', this.pie48.pieData)
        })
      },
      immediate: true,
      deep: true,
    },
    'pie49': {
      handler() {
        this.$nextTick(function () {
          this.drawPie('pie49', this.pie49.pieData)
        })
      },
      immediate: true,
      deep: true,
    }

  },
  mounted() {
//	  window.addEventListener =('beforeunload',function(){
//			alert("111");
//			if(this.flagg){
//				self.time = '00小时00分钟00秒'
//			}
//			
//		})
    var self = this;
    var jiashuju = {
      "name": "数据极客算法建模拉力赛",
      "rankData": [{
        "type": "零售实时榜单",
        "data": [{
          "id": "1001",
          "teamName": "一二三",
          percent: "10",
          "score": "10",
          "change": "0",
          "progress": "22",
          "teamType": "01"
        }, {
          "id": "1002",
          "teamName": "测试2",
          percent: "30",
          "score": "30",
          "change": "0",
          "progress": "33",
          "teamType": "01"
        }, {
          "id": "1005",
          "teamName": "测试5",
          percent: "40",
          "score": "40",
          "change": "0",
          "progress": "44",
          "teamType": "01"
        }, {
          "id": "1004",
          "teamName": "测试4",
          percent: "20",
          "score": "20",
          "change": "0",
          "progress": "11",
          "teamType": "01"
        }, {
          "id": "1003",
          "teamName": "测试3",
          percent: "20",
          "score": "20",
          "change": "0",
          "progress": "30",
          "teamType": "01"
        }]
      }, {
        "type": "运营实时榜单",
        "data": [{
          "id": "1006",
          "teamName": "测试6",
          percent: "60",
          "score": "60",
          "change": "0",
          "progress": "40",
          "teamType": "02"
        }, {
          "id": "1007",
          "teamName": "测试7",
          percent: "70",
          "score": "70",
          "change": "0",
          "progress": "60",
          "teamType": "02"
        }, {
          "id": "10010",
          "teamName": "测试10",
          percent: "40",
          "score": "40",
          "change": "0",
          "progress": "40",
          "teamType": "02"
        }, {
          "id": "1009",
          "teamName": "测试9",
          percent: "80",
          "score": "80",
          "change": "0",
          "progress": "30",
          "teamType": "02"
        }, {
          "id": "1008",
          "teamName": "测试8",
          percent: "20",
          "score": "20",
          "change": "0",
          "progress": "70",
          "teamType": "02"
        }]
      }, {
        "type": "对公实时榜单",
        "data": [{
          "id": "10011",
          "teamName": "测试11",
          percent: "40",
          "score": "40",
          "change": "0",
          "progress": "40",
          "teamType": "03"
        }, {
          "id": "10014",
          "teamName": "测试14",
          percent: "50",
          "score": "50",
          "change": "0",
          "progress": "50",
          "teamType": "03"
        }, {
          "id": "10013",
          "teamName": "测试13",
          percent: "0",
          "score": "0",
          "change": "0",
          "progress": "60",
          "teamType": "03"
        }, {
          "id": "10012",
          "teamName": "测试12",
          percent: "80",
          "score": "80",
          "change": "0",
          "progress": "40",
          "teamType": "03"
        }]
      }, {
        "type": "风险实时榜单",
        "data": [{
          "id": "10015",
          "teamName": "测试15",
          percent: "38",
          "score": "60",
          "change": "0",
          "progress": "40",
          "teamType": "04"
        }, {
          "id": "10018",
          "teamName": "测试18",
          percent: "100",
          "score": "100",
          "change": "0",
          "progress": "30",
          "teamType": "04"
        }, {
          "id": "10017",
          "teamName": "测试17",
          percent: "90",
          "score": "90",
          "change": "0",
          "progress": "30",
          "teamType": "04"
        }, {
          "id": "10016",
          "teamName": "测试16",
          percent: "30",
          "score": "30",
          "change": "0",
          "progress": "30",
          "teamType": "04"
        }]
      }],
      "logData": [{
        "teamID": "1001",
        "teamName": "测试",
        "teamChange": "25",
        "updateTime": "2019-06-14 11:49:40",
        "teamRank": "1"
      }, {
        "teamID": "1012",
        "teamName": "测试",
        "teamChange": "-23",
        "updateTime": "2019-06-14 12:46:28",
        "teamRank": "2"
      }, {
        "teamID": "1003",
        "teamName": "测试",
        "teamChange": "25",
        "updateTime": "2019-06-14 13:49:40",
        "teamRank": "1"
      }, {
        "teamID": "1014",
        "teamName": "测试",
        "teamChange": "-23",
        "updateTime": "2019-06-14 14:46:28",
        "teamRank": "2"
      }, {
        "teamID": "1005",
        "teamName": "测试",
        "teamChange": "25",
        "updateTime": "2019-06-14 15:49:40",
        "teamRank": "1"
      }, {
        "teamID": "1016",
        "teamName": "测试",
        "teamChange": "-23",
        "updateTime": "2019-06-14 16:46:28",
        "teamRank": "2"
      }, {
        "teamID": "1007",
        "teamName": "测试",
        "teamChange": "25",
        "updateTime": "2019-06-14 17:49:40",
        "teamRank": "1"
      }, {
        "teamID": "1018",
        "teamName": "测试",
        "teamChange": "-23",
        "updateTime": "2019-06-14 18:46:28",
        "teamRank": "2"
      }]
    };

    var jiashuju2 = {
      "name": "数据极客算法建模拉力赛",
      "rankData": [{
        "type": "零售实时榜单",
        "data": [{
          "id": "1001",
          "teamName": "一二三一二三",
          percent: "20",
          "score": "20",
          "change": "0",
          "progress": "22",
          "teamType": "01"
        }, {
          "id": "1002",
          "teamName": "测试2",
          percent: "15",
          "score": "15",
          "change": "0",
          "progress": "33",
          "teamType": "01"
        }, {
          "id": "1005",
          "teamName": "测试5",
          percent: "80",
          "score": "80",
          "change": "0",
          "progress": "44",
          "teamType": "01"
        }, {
          "id": "1004",
          "teamName": "测试4",
          percent: "100",
          "score": "100",
          "change": "0",
          "progress": "11",
          "teamType": "01"
        }, {
          "id": "1003",
          "teamName": "测试3",
          percent: "10",
          "score": "10",
          "change": "0",
          "progress": "30",
          "teamType": "01"
        }]
      }, {
        "type": "运营实时榜单",
        "data": [{
          "id": "1006",
          "teamName": "测试6",
          percent: "60",
          "score": "60",
          "change": "0",
          "progress": "40",
          "teamType": "02"
        }, {
          "id": "1007",
          "teamName": "测试7",
          percent: "70",
          "score": "70",
          "change": "0",
          "progress": "60",
          "teamType": "02"
        }, {
          "id": "10010",
          "teamName": "测试10",
          percent: "40",
          "score": "40",
          "change": "0",
          "progress": "40",
          "teamType": "02"
        }, {
          "id": "1009",
          "teamName": "测试9",
          percent: "80",
          "score": "80",
          "change": "0",
          "progress": "30",
          "teamType": "02"
        }, {
          "id": "1008",
          "teamName": "测试8",
          percent: "20",
          "score": "20",
          "change": "0",
          "progress": "70",
          "teamType": "02"
        }]
      }, {
        "type": "对公实时榜单",
        "data": [{
          "id": "10011",
          "teamName": "测试11",
          percent: "40",
          "score": "40",
          "change": "0",
          "progress": "40",
          "teamType": "03"
        }, {
          "id": "10014",
          "teamName": "测试14",
          percent: "50",
          "score": "50",
          "change": "0",
          "progress": "50",
          "teamType": "03"
        }, {
          "id": "10013",
          "teamName": "测试13",
          percent: "0",
          "score": "0",
          "change": "0",
          "progress": "60",
          "teamType": "03"
        }, {
          "id": "10012",
          "teamName": "测试12",
          percent: "80",
          "score": "80",
          "change": "0",
          "progress": "40",
          "teamType": "03"
        }]
      }, {
        "type": "风险实时榜单",
        "data": [{
          "id": "10015",
          "teamName": "测试15",
          percent: "38",
          "score": "38",
          "change": "0",
          "progress": "40",
          "teamType": "04"
        }, {
          "id": "10018",
          "teamName": "测试18",
          percent: "100",
          "score": "100",
          "change": "0",
          "progress": "30",
          "teamType": "04"
        }, {
          "id": "10017",
          "teamName": "测试17",
          percent: "90",
          "score": "90",
          "change": "0",
          "progress": "30",
          "teamType": "04"
        }, {
          "id": "10016",
          "teamName": "测试16",
          percent: "40",
          "score": "40",
          "change": "0",
          "progress": "30",
          "teamType": "04"
        }]
      }],
      "logData": [{
        "teamID": "1001",
        "teamName": "测试",
        "teamChange": "25",
        "updateTime": "2019-06-14 11:49:40",
        "teamRank": "1"
      }, {
        "teamID": "1018",
        "teamName": "测试",
        "teamChange": "-23",
        "updateTime": "2019-06-14 12:46:28",
        "teamRank": "2"
      }, {
        "teamID": "1001",
        "teamName": "测试",
        "teamChange": "25",
        "updateTime": "2019-06-14 13:49:40",
        "teamRank": "1"
      }, {
        "teamID": "1018",
        "teamName": "测试",
        "teamChange": "-23",
        "updateTime": "2019-06-14 14:46:28",
        "teamRank": "2"
      }, {
        "teamID": "1001",
        "teamName": "测试",
        "teamChange": "25",
        "updateTime": "2019-06-14 15:49:40",
        "teamRank": "1"
      }, {
        "teamID": "1018",
        "teamName": "测试",
        "teamChange": "-23",
        "updateTime": "2019-06-14 16:46:28",
        "teamRank": "2"
      }, {
        "teamID": "1001",
        "teamName": "测试",
        "teamChange": "25",
        "updateTime": "2019-06-14 17:49:40",
        "teamRank": "1"
      }, {
        "teamID": "1018",
        "teamName": "测试",
        "teamChange": "-23",
        "updateTime": "2019-06-14 18:46:28",
        "teamRank": "2"
      }]
    };

    document.onkeydown = function (e) {
    	console.log(11)
      let key = window.event.keyCode;
      if (key == 13 /*&& e.ctrlKey*/) {
    	  console.log(self.startShow)
        if (!self.startShow) {
          return
        }
        self.startShow = false
        ajaxStartTime()
        self.countDown(self.countDownTime) //时间初始化 24小时
      }
    };


    function ajaxStartTime() {
      axios({
          method: 'get',
          url: 'startSASGame',

        }).then(function (response) {
          console.log('发送成功')
        })
        .catch(function (error) {
          console.log(error);
        });
    }
//    setTimeout(() => {
//      self.processData(jiashuju)
//    }, 3000);
    var timeer
    ajax()

    function ajax() {
      axios({
          method: 'get',
          url: 'getSASGamePageData',

        }).then(function (response) {
          clearTimeout(timeer)
          self.processData(response.data)
          timeer = setTimeout(ajax, 2000);
        })
        .catch(function (error) {
          console.log(error);
        });
    }
    ajaxGetTime()

    function ajaxGetTime() {
      axios({
          method: 'get',
          url: 'getSASGameTime',

        }).then(function (response) {
          self.countDownTime = response.data
          startFlag()

        })
        .catch(function (error) {
          console.log(error);
        });
    }
    
    function startFlag() {
    	 console.log(self.countDownTime)
      if (self.countDownTime == 10000 /*86400000*/) {
        return
      } else {
        self.startShow = false
        self.countDown(self.countDownTime) //时间初始化 24小时
      }
    }

//    setTimeout(() => {
//      jiashuju = jiashuju2
//      // this.sortData (jiashuju.rankData[0].data)
//      self.processData(jiashuju)
//      // self.bf()
//
//    }, 6000);

    // setInterval(() => {
    //   if (this.offsetTopFlag) {
    //     this.offsetTopNumber = this.offsetTopNumber - 0.4
    //     if (this.offsetTopNumber < -100) {
    //       this.offsetTopFlag = false
    //       // this.offsetTopNumber = 0
    //     }
    //   } else {
    //     this.offsetTopNumber = this.offsetTopNumber + 0.4
    //     if (this.offsetTopNumber > 0) {
    //       this.offsetTopFlag = true
    //     }
    //   }

    // }, 30)

    // var slidTimer
    // function slid() {
    //   if (self.offsetTopFlag) {
    //     self.offsetTopNumber = self.offsetTopNumber - 10
    //     if (self.offsetTopNumber < -100) {
    //       self.offsetTopFlag = false
    //       // self.offsetTopNumber = 0
    //     }
    //   } else {
    //     self.offsetTopNumber = self.offsetTopNumber + 10
    //     if (self.offsetTopNumber > 0) {
    //       self.offsetTopFlag = true
    //     }
    //   }
    //   console.log(111)
    //   clearTimeout(slidTimer)
    //   slidTimer = setTimeout(slid, 1000);
    // }
    // slid()




    this.$nextTick(function () {
      this.drawPie('pie11', this.pie11.pieData)
      this.drawPie('pie12', this.pie12.pieData)
      this.drawPie('pie13', this.pie13.pieData)
      this.drawPie('pie14', this.pie14.pieData)
      this.drawPie('pie15', this.pie15.pieData)
      this.drawPie('pie16', this.pie16.pieData)
      this.drawPie('pie17', this.pie17.pieData)
      this.drawPie('pie18', this.pie18.pieData)
      this.drawPie('pie19', this.pie19.pieData)


      this.drawPie('pie21', this.pie21.pieData)
      this.drawPie('pie22', this.pie22.pieData)
      this.drawPie('pie23', this.pie23.pieData)
      this.drawPie('pie24', this.pie24.pieData)
      this.drawPie('pie25', this.pie25.pieData)
      this.drawPie('pie26', this.pie26.pieData)
      this.drawPie('pie27', this.pie27.pieData)
      this.drawPie('pie28', this.pie28.pieData)
      this.drawPie('pie29', this.pie29.pieData)

      this.drawPie('pie31', this.pie31.pieData)
      this.drawPie('pie32', this.pie32.pieData)
      this.drawPie('pie33', this.pie33.pieData)
      this.drawPie('pie34', this.pie34.pieData)
      this.drawPie('pie35', this.pie35.pieData)
      this.drawPie('pie36', this.pie36.pieData)
      this.drawPie('pie37', this.pie37.pieData)
      this.drawPie('pie38', this.pie38.pieData)
      this.drawPie('pie39', this.pie39.pieData)

      this.drawPie('pie41', this.pie41.pieData)
      this.drawPie('pie42', this.pie42.pieData)
      this.drawPie('pie43', this.pie43.pieData)
      this.drawPie('pie44', this.pie44.pieData)
      this.drawPie('pie45', this.pie45.pieData)
      this.drawPie('pie46', this.pie46.pieData)
      this.drawPie('pie47', this.pie47.pieData)
      this.drawPie('pie48', this.pie48.pieData)
      this.drawPie('pie49', this.pie49.pieData)
    })
  }
})