function delCustomer(id){
    if (confirm('是否确认删除')){
        window.location.href='customer.do?id=' + id + '&operate=delete';
    }
}
function toPage(pageNum){
    window.location.href='customer.do?pageNum=' + pageNum + '&operate=index';
}