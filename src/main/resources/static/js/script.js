console.log("script loaded")

let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", ()=> {
    changeTheme(currentTheme);
})

function changeTheme() {
    document.querySelector('html').classList.add(currentTheme);
    document.getElementById("theme-change-btn").addEventListener("click", () => {
        let oldTheme = currentTheme;

        console.log("theme change clicked")
        if (currentTheme === "light") {
            currentTheme = "dark";
        } else {
            currentTheme = "light";
        }
        setTheme(currentTheme);
        document.querySelector('html').classList.remove(oldTheme);
        document.querySelector('html').classList.add(currentTheme);
    })

}

function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

function getTheme() {
    let theme = localStorage.getItem("theme");
    if (theme) {
        return theme;
    } else {
        return "light";
    }
}