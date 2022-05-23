var str = "victor";

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
alert(capitalizeFirstLetter(str));