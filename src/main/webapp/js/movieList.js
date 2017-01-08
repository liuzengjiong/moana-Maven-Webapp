
var ticketTotal = 0; //初始条数
var requestPage = 1; //请求页数
var pageRows = 10; //每页显示行数
var ids = [];  //每页的id数组
var timerWork ; //定时任务



window.onload = function () {
    var oTable = document.getElementById('list');//列表
    var oUser = document.getElementById('user');
    var oContainer = document.getElementById('container');
    var oContent = document.getElementById('content');
    var oCancel = document.getElementById('cancel');//取消按钮
    var oAddWrap = document.getElementById('wrap');//添加外层
    var oAddPanel = document.getElementById('addPanel');//添加层
    var aInput = oAddPanel.getElementsByTagName('input');//添加课程时输入的名称与教师
    var oSure = document.getElementById('sure');//确定添加按钮
    var aSpan = document.getElementById('buyBtn');//购买按钮
    
    var index = 0;
    setVideoPagination();
    //刷新列表
    refleshList();
    //oContainer.style.height = 200+oContent.offsetHeight+50+'px';
    $("#quit").click(function(){
    	window.location.href = getContextPath();
    });
  //设置分页控件
    function setVideoPagination(){
        $("#div_pagination").html('<ul id="pagination" class="pagination-sm"></ul>');  //该控件在不刷新页面情况下无法更改页数，只能用此方法实现页数的变动
    	var totalPage;
    	if(ticketTotal == 0){
    		totalPage = 1;
    	}else{
    		totalPage = Math.ceil(ticketTotal/pageRows);
    	}
    	$('#div_pagination ul').twbsPagination({
            totalPages: totalPage,	//Math.ceil为向上取整
            visiblePages: 20,
            startPage: requestPage,
            first: "首页",  
            prev: "上一页",  
            next: "下一页",  
            last: "未页", 
            initiateStartPageClick:false,
            onPageClick: function (event, page) {
            	requestPage = page;
            	refleshList();
            }
        });
    }

    //取消添加操作
    oCancel.onclick = function () {
        oAddWrap.style.display = 'none';
        $("#buy_ticket_sum_pricce").html("0");
    }
    
    $("#buy_ticket_num").keyup(function(e){
    	onlyIntegerKeyUp(e); //只允许正整数
    	
    	var price = $("#buy_ticket_price").html();
    	var num = $("#buy_ticket_num").val();
    	var sumPrice = price * num;
    	$("#buy_ticket_sum_pricce").html(sumPrice);
    	$("#sum_price_input").val(sumPrice);
    	
    });
    $("#sure").click(function(){
    	var num = $("#buy_ticket_num").val();
    	var pattern = /[^\d]/ig;
    	if(pattern.test(num)) {
    		alert("请输入数字");
    		$("#buy_ticket_num").val("").focus();
    		return;
    	}
    	submitForm($("#form"));
    });
    
    function submitForm(form){
	    $(form).ajaxSubmit(
	        {
	            url: getContextPath()+"/order/add",                 //默认是form的action
	            //type: type,               //默认是form的method（get or post）
	            dataType: "json",           //html(默认), xml, script, json...接受服务端返回的类型
	            clearForm: true,          //成功提交后，清除所有表单元素的值
	            //resetForm: true,          //成功提交后，重置所有表单元素的值
	            timeout: 3000,               //限制请求的时间，当请求大于3秒后，跳出请求
	            //提交前的回调函数
	            beforeSubmit: function(arr,$form,options){
	                //formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]
	                //jqForm:   jQuery对象，封装了表单的元素
	                //options:  options对象
	                //比如可以再表单提交前进行表单验证
	                console.log("beforeSubmit",arr,$form,options)
	            },
	            //提交成功后的回调函数
	            success: function(data,status,xhr,$form){
	                console.log("success",data,status,xhr,$form);
	                oAddWrap.style.display = 'none';
	                refleshTicketNum()
	                alert(data.msg);
	            },
	            error: function(xhr, status, error, $form){
	                console.log("error",xhr, status, error, $form)
	            },
	            complete: function(xhr, status, $form){
	                console.log("complete",xhr, status, $form)
	            }
	        }
	    );
	    return false; //阻止表单默认提交
	}
    
    //刷新票数
    function refleshTicketNum(){
    	 $.ajax({
             url: getContextPath()+"/ticket/remainNum",    //请求的url地址
             dataType: "json",   //返回格式为json
             async: true, 
             data: {
             	"ids":ids
             },    //参数值
             type: "GET",   //请求方式
             traditional: true,  //避免数组被自动转换
             beforeSend: function () {
                 //请求前的处理
             },
             success: function (data) {
            	 if(data.code === '1'){
            		 var results = data.list;
            		 $.each(results,function(index,item){
            			 
            			 var id_obj = $("#"+results[index].id);
            			 if(id_obj){
            				 id_obj.html(results[index].remainNum);
            			 }
            			 
            		 });
            	 }else{
            		 if(timerWork){
                      	clearInterval(timerWork);
                      }
            		 if(data.msg){
            			 alert(msg);
            			 return;
            		 }
            		 alert("获取票数失败，请刷新页面");
            	 }
             }
    	 });
    }
    
    //刷新列表
    function refleshList() {
        //JQuery ajax()请求数据
        $.ajax({
            url: getContextPath()+"/ticket/list",    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            data: {
            	"page":requestPage,
            	"rows":pageRows
            },    //参数值
            type: "GET",   //请求方式
            beforeSend: function () {
                //请求前的处理
            },
            success: function (data) {
                //请求成功时处理
                if (data.code === '1') {
                	ticketTotal=  data.total;
                	//oTable.innerHTML = '';//清除列表
                	var trs = oTable.getElementsByTagName("tr");
                	for(var i = trs.length - 1; i > 0; i--) {
                		oTable.deleteRow(i);
                    }
                    oUser.innerHTML = data.username;
                    //添加查看订单选项
                    var orderList = document.createElement('a');
                    orderList.href = getContextPath()+"/html/orderList.html";
                    orderList.innerHTML = "【查看订单】"
                    oUser.appendChild(orderList);
                    
                    ids.splice(0,ids.length); //清空数组
                    for (var i = 0; i < data.size; i++) {
                    	
                    	var oTr = document.createElement('tr');
                    	var oTd_1 = document.createElement('td');
                    	var oTd_2 = document.createElement('td');
                    	var oTd_3= document.createElement('td');
                    	var oTd_4 = document.createElement('td');
                    	var oTd_5 = document.createElement('td');
                    	var oTd_6 = document.createElement('td');
                    	
                    	oTd_1.innerHTML = data.list[i].movieName;
                    	$(oTd_1).addClass("movie_name");
                    	oTd_2.innerHTML = data.list[i].price;
                    	$(oTd_2).addClass("movie_price");
                    	oTd_3.innerHTML = data.list[i].cinemaName;
                    	$(oTd_3).addClass("cinema_name");
                    	oTd_4.innerHTML = data.list[i].displayTime;
                    	$(oTd_4).addClass("display_time");
                    	oTd_5.innerHTML = data.list[i].remainNum;
                    	$(oTd_5).addClass("remain_num");
                    	oTd_5.id = data.list[i].id;
                    	var oBuy = document.createElement('a');//抢购
                    	oBuy.innerHTML = '抢购';
                    	oBuy.href = "javascript:void(0)";
                    	oTd_6.appendChild(oBuy);
                    	
                        oTr.appendChild(oTd_1);
                        oTr.appendChild(oTd_2);
                        oTr.appendChild(oTd_3);
                        oTr.appendChild(oTd_4);
                        oTr.appendChild(oTd_5);
                        oTr.appendChild(oTd_6);
                        
                        oTable.appendChild(oTr);
                        
                        oContainer.style.height = 200+oContent.offsetHeight+50+'px';
                        
                        ids.push(data.list[i].id);
                        
                        //购买操作
                        oBuy.onclick = function (e) {
                        	e = e || window.event; 
                            var obj=e.target || e.srcElement;
                            
	                    	var thisTd = $(obj).parent("td");
	                    	var thisTr = thisTd.parent("tr");
	                    	
	                    	var price = thisTr.find(".movie_price").html();
	                    	var movieName = thisTr.find(".movie_name").html();
	                    	var cinemaName = thisTr.find(".cinema_name").html();
	                    	var displayTime = thisTr.find(".display_time").html();
	                    	var ticketId = thisTr.find(".remain_num").attr('id');
	                    	
	                    	$("#buy_ticket_price").html(price);
	                    	$("#buy_ticket_display_time").html(displayTime);
	                    	$("#buy_ticket_name").html(movieName);
	                    	$("#buy_ticket_cinema_name").html(cinemaName);
	                    	$("#buy_ticket_id").val(ticketId);
	                    	$("#buy_ticket_sum_pricce").html("0");
	                        oAddWrap.style.display = 'block';
	                        $("#buy_ticket_num").val("").focus();
	                    
	                    }
                        
                        //设置定时刷新剩余票数的任务
                        if(timerWork){
                        	clearInterval(timerWork);
                        }
                        timerWork = setInterval(refleshTicketNum,3000);
                    }
                }
            }
        });
    }
}
