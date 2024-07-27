const uploadImgPreview = document.getElementById("upload_img_preview");
const uploadImg = document.getElementById("dropzone-file");

uploadImg.addEventListener("change", function (event) {
  const file = event.target.files[0];
  if (file) {
    let reader = new FileReader();
    reader.onload = () => {
      uploadImgPreview.setAttribute("src", reader.result);
    };
    reader.readAsDataURL(file);
  }
});
