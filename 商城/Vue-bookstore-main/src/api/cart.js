import request from './request.js'


export function listCart(uid){
    let url='/api/cart/listCart?uid='+uid
    return request.get(url)
}
//Integer uid,Integer bookID,Integer count
export function addCart(data){
    return request.post('/api/cart/addCart',data)
}
//Integer uid,Integer bookID,Integer count
export function updateCart(data){
    return request.post('/api/cart/updateCart',data)
}

export function deleteCart(cid){
    let url='/api/cart/deleteCart?cid='+cid
    return request.get(url)
}

export function dropCartById(uid){
    let url='/api/cart/dropCartById?uid='+uid
    return request.get(url)
}