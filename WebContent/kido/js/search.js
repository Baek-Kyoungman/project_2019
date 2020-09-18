var sggcode0 = new Array("행정구역", "");
var sggcode1 = new Array('행정구역', '강남구', '강동구', '강북구', '강서구', '관악구', '광진구',
		'구로구', '금천구', '노원구', '도봉구', '동대문구', '동작구', '마포구', '서대문구', '서초구', '성동구',
		'성북구', '송파구', '양천구', '영등포구', '용산구', '은평구', '종로구', '중구', '중랑구');
var sggcode2 = new Array("없음");
var sggcode3 = new Array("없음");
var sggcode4 = new Array("없음");
var sggcode5 = new Array("없음");
var sggcode6 = new Array("없음");
var sggcode4 = new Array("없음");
var sggcode5 = new Array("없음");
var sggcode6 = new Array("없음");

function addr(item) {
	var temp, i = 0, j = 0;
	var ccount, cselect;

	temp = document.Addr.sggcode;

	for (i = (temp.options.length - 1); i > 0; i--) {
		temp.options[i] = null;
	}
	eval('ccount = sggcode' + item + '.length');
	for (j = 0; j < ccount; j++) {
		eval('cselect = sggcode' + item + '[' + j + '];');
		temp.options[j] = new Option(cselect, cselect);
	}
	temp.options[0].selected = true;
	return true;
}