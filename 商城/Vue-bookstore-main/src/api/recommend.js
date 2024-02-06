import request from './request.js'

export function userCF(uid){
    let url='/api/userCF?uid='+uid
    return request.get(url)

}
export function itemCF(bookID){
    let url='/api/itemCF?bookID='+bookID
    return request.get(url)
}
export function listRecommend(){
    let url='/api/listRecommend'
    return request.get(url)
}
//Integer bookID, String bookName, String author, String cover, Double price
export function insertRecommend(data){
    return request.post('/api/insertRecommend',data)
}

export function deleteRecommend(bookID){
    let url='/api/deleteRecommend?bookID='+bookID
    return request.get(url)

}