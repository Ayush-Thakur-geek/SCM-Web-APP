const uploadImgPreview = document.getElementById("upload_img_preview");

const uploadImg = document.getElementById("dropzone-file");

uploadImg.addEventListener("change", function () {
  const file = event.target.files[0];
  let reader = new FileReader();
  reader.onload = () => {
    uploadImgPreview.setAttribute("src", reader.result);
  };
  reader.readAsDataURL(file);
});
