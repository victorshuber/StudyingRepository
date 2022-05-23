var str = "$265";

function extractCurrencyValue(str) {
    return +str.slice(1);
}
alert(extractCurrencyValue(str));