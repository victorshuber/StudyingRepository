const dividers = (number) => {
    let arr = [];
    for (let i = number; i > 0; i--) {
        if (number % i == 0) {
            arr = arr + (i + " ");
        }
    }
    arr
    alert(arr);
}
var number = prompt("Enter the number ");

dividers(number);