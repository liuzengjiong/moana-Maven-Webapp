
var orderTotal = 0; //初始条数
var requestPage = 1; //请求页数
var pageRows = 10; //每页显示行数


//设置分页控件
function setPagination(){
    $("#div_pagination").html('<ul id="pagination" class="pagination-sm"></ul>');  //该控件在不刷新页面情况下无法更改页数，只能用此方法实现页数的变动
	var totalPage;
	if(orderTotal == 0){
		totalPage = 1;
	}else{
		totalPage = Math.ceil(orderTotal/pageRows);
	}
	$('#div_pagination ul').twbsPagination({
        totalPages: totalPage,	//Math.ceil为向上取整
        visiblePages: 20,
        startPage: requestPage,
        initiateStartPageClick:false,
        first: "首页",  
        prev: "上一页",  
        next: "下一页",  
        last: "未页",  
        onPageClick: function (event, page) {	
        	requestPage = page;
        	refleshList();
        }
    });
}
//刷新列表
function refleshList() {
    //JQuery ajax()请求数据
    $.ajax({
        url: getContextPath()+"/order/list",    //请求的url地址
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
            if (data.code == '1001') {
            	
            	 var oTable = document.getElementById('list');//列表
        	    var oUser = document.getElementById('user');
        	    var oContainer = document.getElementById('container');
        	    var oContent = document.getElementById('content');
            	
            	orderTotal=  data.data.total;
            	setPagination();
            	//oTable.innerHTML = '';//清除列表
            	var trs = oTable.getElementsByTagName("tr");
            	for(var i = trs.length - 1; i > 0; i--) {
            		oTable.deleteRow(i);
                }
                oUser.innerHTML = data.data.username;
              //添加查看订单选项
                var movieList = document.createElement('a');
                movieList.href = getContextPath()+"/html/movieList.html";
                movieList.innerHTML = "【影票列表】"
                oUser.appendChild(movieList);
                
                
                var list = data.data.list;
                for (var i = 0; i < list.length; i++) {
                	var oTr = document.createElement('tr');
                	var oTd_1 = document.createElement('td');
                	var oTd_2 = document.createElement('td');
                	var oTd_3= document.createElement('td');
                	var oTd_4 = document.createElement('td');
                	var oTd_5 = document.createElement('td');
                	var oTd_6 = document.createElement('td');
                	
                	oTd_1.innerHTML = list[i].movieName;
                	oTd_2.innerHTML = list[i].cinemaName;
                	oTd_3.innerHTML = list[i].displayTime;
                	oTd_4.innerHTML = list[i].purchaseTime;
                	oTd_5.innerHTML = list[i].purchaseNum;
                	oTd_6.innerHTML = list[i].sumPrice;
                	
                    oTr.appendChild(oTd_1);
                    oTr.appendChild(oTd_2);
                    oTr.appendChild(oTd_3);
                    oTr.appendChild(oTd_4);
                    oTr.appendChild(oTd_5);
                    oTr.appendChild(oTd_6);
                    
                    oTable.appendChild(oTr);
                    
                    oContainer.style.height = 200+oContent.offsetHeight+50+'px';
                    
                }
            }
        }
    });
}
window.onload = function () {
   
    $("#quit").click(function(){
    	window.location.href = getContextPath()+"/index.jsp";
    });
    setPagination();
    //刷新列表
    refleshList();
    //oContainer.style.height = 200+oContent.offsetHeight+50+'px';
    
    
}
