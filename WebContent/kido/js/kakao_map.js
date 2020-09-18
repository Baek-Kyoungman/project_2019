
	// GeoLocation을 이용해서 접속 위치를 얻어옵니다
	navigator.geolocation.getCurrentPosition(function(position) {
    
    var lat = position.coords.latitude, // 위도
        lon = position.coords.longitude; // 경도

    
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
            level: 4 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); 

    // 지도 타입 변경 컨트롤을 생성한다
	var mapTypeControl = new kakao.maps.MapTypeControl();

	// 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
    
	//지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	$('#Addr_Search').click(function() {
		
		
		var Addr_area = $("#sido").val() 
			Addr_area += $("#sigungu").val();
		console.log(Addr_area)
		
		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places();

		// 키워드로 장소를 검색합니다
		ps.keywordSearch(Addr_area, placesSearchCB);

		// 키워드 검색 완료 시 호출되는 콜백함수 입니다
		function placesSearchCB(data, status, pagination) {
			if (status === kakao.maps.services.Status.OK) {

				// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
				// LatLngBounds 객체에 좌표를 추가합니다
				var bounds = new kakao.maps.LatLngBounds();

				for (var i = 0; i < data.length; i++) {
					bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
				}

				// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
				map.setBounds(bounds);
				map.setZoomqble(false);
			}
		}
		map.setLevle(2);
	});
	
		var json_url = "/json/kinder_data.json";
		
		// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
		$.getJSON(json_url, function (data, textStatus) {
			var KID_SEQ = null;
			var KINDERNAME = null;
			var addr = null;
			var LAT = null;
			var LNG = null;
	        
	        $.each(data.kinderInfo, function () {
	        	KID_SEQ = this.kid_seq;
	        	KINDERNAME = this.KINDERNAME;
	        	addr = this.addr;
	            LAT = this.LAT;
	            LNG = this.LNG;
	
	            // 마커를 생성합니다
	            var marker = new kakao.maps.Marker({
	                map: map, // 마커를 표시할 지도
	                position: new kakao.maps.LatLng(LNG, LAT), // 마커를 표시할 위치
	                clickable: true
	            });
	            
	            
	            // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
	            var iwContent = 
	                ' <div class="wrap">' + 
	                '    <div class="info">' + 
	                '        <div class="title">' + 
	                			 KINDERNAME  + 
	                '        </div>' + 
	                '        <div class="body">' + 
	                '            <div class="desc">' + 
	                '                <div class="ellipsis">'
	                					+ addr +
	                '				 </div>' + 
	                '                <div style="margin=5px;"><a href="/Map_search_detail.do?kid_seq=' + KID_SEQ + '" target="_blank" class="link">더보기</a><a href="https://map.kakao.com/link/to/'+ KINDERNAME +','+ LNG +' , '+ LAT +'" class="link" target="_blank">길찾기</a></div>' +
	                '            </div>' + 
	                '        </div>' + 
	                '    </div>' +     
	                '</div>',
	                iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
	
	            
	            // 인포윈도우를 생성합니다
	            var infowindow = new kakao.maps.InfoWindow({
	                content : iwContent,
	                removable : iwRemoveable
	            });
	
	            // 마커에 클릭이벤트를 등록합니다
	            kakao.maps.event.addListener(marker, 'click', function() {
	                  // 마커 위에 인포윈도우를 표시합니다
	                  infowindow.open(map, marker);  
	            });
	           console.log(KINDERNAME + " : " + LAT + " , " + LNG + "," + addr);
	            
	
	        });
	 
	   });
});
	
	var listText = new Array();

	var listValue = new Array();

	listText[0] = new Array('행정구역', '강남구', '강동구', '강북구', '강서구', '관악구',
			'광진구', '구로구', '금천구', '노원구', '도봉구', '동대문구', '동작구', '마포구',
			'서대문구', '서초구', '성동구', '성북구', '송파구', '양천구', '영등포구', '용산구',
			'은평구', '종로구', '중구', '중랑구'); // 각 option의 text

	listValue[0] = new Array('행정구역', '강남구', '강동구', '강북구', '강서구', '관악구',
			'광진구', '구로구', '금천구', '노원구', '도봉구', '동대문구', '동작구', '마포구',
			'서대문구', '서초구', '성동구', '성북구', '송파구', '양천구', '영등포구', '용산구',
			'은평구', '종로구', '중구', '중랑구'); // 각 option의 value

	function getList(sel_id) {

		var sel = document.getElementsByName('selName');

		var sel_id = sel_id - 1;

		if (sel_id >= 0) {

			sel[1].options.length = listText[sel_id].length;

			for (i = 0; i

			< listText[sel_id].length; i++) {

				sel[1].options[i] = new Option(listText[sel_id][i],
						listValue[sel_id][i]);

			}

		} else {

			sel[1].options.length = 1;

			sel[1].options[0].text = "행정구역";

			sel[1].options[0].value = 0;

		}

	}
	
	
	
/*$('document').ready(function() {
	var area0 = ['지역','서울특별시']
	var area1 = ['행정구역','강남구', '강동구', '강북구', '강서구', '관악구', '광진구', '구로구', '금천구', '노원구', '도봉구', '동대문구', '동작구', '마포구', '서대문구', '서초구', '성동구', '성북구', '송파구', '양천구', '영등포구', '용산구', '은평구', '종로구', '중구', '중랑구']
	
	// 행정구역 선택박스 초기화
	$("select[name^=sido]").each(function() {
		$selsido = $(this);
		$.each(eval(area0),function(){
			$selsido.append("<option value='"+this+"'>"+this+"</option>");
		});
		$selsido.next().append("<option value=''>지역</option>");
	});
	
	$("select[name^=sido]").change(function() {
		var area = "area"+$("option",$(this)).index($("option:selected",$(this)));
		var $gungu = $(this).next();
		$("option",$gungu).removew();
		
		if(area == "area0")
			$gungu.append("<option>",)
	}
	}
})	*/

