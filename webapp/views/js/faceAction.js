// JavaScript Document
	function hideUploadFrame(){
	
		$(".upface").html('<canvas   id="upface" width="300" height="300"></canvas>') 
		$(".face").html('<canvas id="face" width="200" height="200"></canvas>') 
		var oImage = document.getElementById('preview');
		var mycv = document.getElementById("upface");  
	
		var myctx = mycv.getContext("2d");

		myctx.drawImage(oImage,$('#x1').val(),$('#y1').val(),$('#w').val(),$('#h').val(),0,0,300,300)
		
		var face = document.getElementById("face");  
		var faceCanvas = face.getContext("2d");
		faceCanvas.drawImage(oImage,$('#x1').val(),$('#y1').val(),$('#w').val(),$('#h').val(),0,0,200,200)
		$(".uploadFrame").hide()
		
		/*
		提交头像代码，头像大小大200x200,小 64x64
		var canvas = document.getElementById("face");  

		var imgData =canvas.toDataURL("image/jpeg");
		$.post('http://localhost/upload.php',{'data': imgData },function(data){	alert(data.flag)},"json")
		*/
	
		
		
	}
	
	function noUploadFrame(){
		$(".uploadFrame").hide()
		
	}
	function fileSelectHandler() {
		$(".uploadFrame").show()
    // get selected file
    var oFile = $('#fileupload')[0].files[0];

/*
    // check for image type (jpg and png are allowed)
    var rFilter = /^(image\/jpeg|image\/png)$/i;
    if (! rFilter.test(oFile.type)) {
        alert('请选择图片jpg、png格式');
        return;
    }
*/
/*
    if (oFile.size > 250 * 1024) {
        alert('图片尺寸过大');
        return;
    }
*/	
  $(".preview").html(' <span>图片加载中。。。</span> <canvas   id="preview" width="640" height="900"></canvas>')
 
  var oImage = new Image()
  //document.getElementById('preview');


    // prepare HTML5 FileReader
    var oReader = new FileReader();
        oReader.onload = function(e) {

        // e.target.result contains the DataURL which we can use as a source of the image
       
	    oImage.src = e.target.result;
		oImage.onerror=function(){
			alert("图片加载失败~")	
			noUploadFrame();
		}
        oImage.onload = function () { // onload event handler

            // display some basic image info
            var sResultFileSize = bytesToSize(oFile.size);
            $('#filesize').val(sResultFileSize);
            $('#filetype').val(oFile.type);
            $('#filedim').val(oImage.naturalWidth + ' x ' + oImage.naturalHeight);

	

		var face = document.getElementById("preview");  

		var faceCanvas = face.getContext("2d");
		
		//faceCanvas.drawColor("#000"); 
			faceCanvas.fillStyle="#000";
			faceCanvas.fillRect(0,0,640,900);
			oNh=oImage.naturalHeight
			oNw=oImage.naturalWidth
			//alert(oNh+":"+oNw)
			if(oNh>1500 || oNw>1500){
				showMsgTobig()
				noUploadFrame();
				return;
			}
			
			nH=Math.floor(2448*640/3264)


			nH=Math.floor(oNh*640/oNw)
	   		gy=(900-nH)*0.5
			//alert(oNh+":"+nH)
			faceCanvas.drawImage(oImage,0,0,oNw,oNh,0,gy,640,nH)
			/*
		if(oImage.naturalWidth/oImage.naturalHeight > 640/900){
			nH=Math.floor(oImage.naturalHeight*640/oImage.naturalWidth)
	   		gy=(900-nH)*0.5
			faceCanvas.drawImage(oImage,0,0,oImage.naturalWidth,oImage.naturalHeight,0,gy,640,nH)
		}else{
			
			nW=Math.floor(oImage.naturalWidth*900/oImage.naturalHeight)
	   		gx=(640-nW)*0.5
			faceCanvas.drawImage(oImage,0,0,oImage.naturalWidth,oImage.naturalHeight,gx,0,nW,900)
		}
		*/
		  $(".preview span").hide()
		//oImage.parentNode.removeChild(oImage);
		 $(".add").html('<input type="file" accept="image/*"   id="fileupload" name="fileupload" onChange="fileSelectHandler()"/>')
			
            // Create variables (in this scope) to hold the Jcrop API and image size
            var jcrop_api, boundx, boundy;
	
            // destroy Jcrop if it is existed
            if (typeof jcrop_api != 'undefined') 
                jcrop_api.destroy();

            // initialize Jcrop
            $('#preview').Jcrop({
                minSize: [640, 640], // min crop size
				maxSize: [640, 640], 
				setSelect: [0,130,640,640],
                aspectRatio : 1, // keep aspect ratio 1:1
				handleOpacity: 0.5,	//缩放按钮透明度
				handleSize: 9,
				cornerHandles: false,
				sideHandles:false ,
				allowResize:false,
                bgFade: false, // use fade effect
                bgOpacity: .3, // fade opacity
                onChange: updateInfo,
                onSelect: updateInfo,
                onRelease: clearInfo
            }, function(){
			
                // use the Jcrop API to get the real image size
                var bounds = this.getBounds();
                boundx = bounds[0];
                boundy = bounds[1];

                // Store the Jcrop API in the jcrop_api variable
                jcrop_api = this;
            });
			// window.URL.revokeObjectURL(this.src); 
        };
    };

    // read selected file as DataURL

   oReader.readAsDataURL(oFile);

	//oFile.reset();//清除

}


function updateInfo(e) {
	
    $('#x1').val(e.x);
    $('#y1').val(e.y);
    $('#x2').val(e.x2);
    $('#y2').val(e.y2);
    $('#w').val(e.w);
    $('#h').val(e.h);
	
};

function bytesToSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB'];
    if (bytes == 0) return 'n/a';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return (bytes / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
};

function checkForm() {
    if (parseInt($('#w').val())) return true;
    $('.error').html('Please select a crop region and then press Upload').show();
    return false;
};
function clearInfo() {
    $('.info #w').val('');
    $('.info #h').val('');
};