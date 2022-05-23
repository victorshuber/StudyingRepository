let num = prompt("Enter the number: ");

if (!isNaN(Number(num))) {
    if (num > 0)
        alert("1");
    if (num < 0)
        alert("-1");
    if (num == 0)
        alert("0");
} else
    alert("Error")
    //alert(Number("cfa"));