var a = 19;
var b = 43;
var c = 7;
var arr = [a, b, c];
var temp = 0;
for (let i = 0; i <= arr.length; i++) {
    for (let j = 0; j <= arr.length; j++) {
        if (arr[j] > arr[j + 1]) {
            temp = arr[j + 1];
            arr[j + 1] = arr[j];
            arr[j] = temp;
        }
    }

}
var min = arr[0];
var max = arr[arr.length - 1];
alert('Min number: ' + min + " & max number:" + max);