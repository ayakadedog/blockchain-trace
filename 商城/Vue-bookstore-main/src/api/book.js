import request from './request.js'

export function showBook(){
    return request.get('/api/book/showBook')
}
export function selectBookByType(type){
    return request.get('/api/book/selectBookByType?type='+type)
}
export function showItem(bookID){
    let url='/api/book/showItem?bookID='+bookID
    return request.get(url)
}
export function insertBook(book){
    return request.post('/api/book/insertBook',book)
}
export function updateBook(updata){
    return request.post('/api/book/updateBook',updata)
}
export function updateBookType(bookID, type){
    let url='/api/book/updateBookType?bookID='+bookID+'&type='+type
    return request.get(url)
}
export function deleteBook(bookID){
    let url='/api/book/deleteBook?bookID='+bookID
    return request.get(url)

}
export function upload(formData){
    return request.post('/api/upload',formData)
}

export function searchBook(name){
    let url='/api/book/searchBook?bookName='+name
    return request.get(url)

}
export function updateStock(bookID, stock){
    let url='/api/book/updateStock?bookID='+bookID+'&stock='+stock
    return request.get(url)
}