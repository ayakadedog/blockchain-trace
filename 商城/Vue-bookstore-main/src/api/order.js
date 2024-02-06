import request from './request.js'

//Integer uid,Double total,String paymentWay,String deliverWay,String orderState
export function insertOrders(data){
    return request.post('/api/order/insertOrders',data)
}


export function getOrderById(orderID){
    let url='/api/order/getOrderById?orderID='+orderID
    return request.get(url)
}
export function getOrders(uid){
    let url='/api/order/getOrders?uid='+uid
    return request.get(url)
}

export function updateState(orderID, orderState){
    let url='/api/order/updateState?orderID='+orderID+'&orderState='+orderState
    return request.get(url)
}

export function getAllOrders(){
    return request.get('/api/order/getAllOrders')
}

export function insertSuborders(data){
    return request.post('/api/order/insertSuborders',data)
}

export function getSuborders(orderID){
    let url='/api/order/getSuborders?orderID='+orderID
    return request.get(url)
}