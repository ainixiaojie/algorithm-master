// 查询列表接口
const getClassificationPage = (params) => {
    return $axios({
        url: '/alertClassification/page',
        method: 'get',
        params
    })
}

// 编辑页面反查详情接口
const queryClassificationById = (id) => {
    return $axios({
        url: `/alertClassification/${id}`,
        method: 'get'
    })
}

// 删除当前列的接口
const deleteClassification= (id) => {
    return $axios({
        url: '/alertClassification',
        method: 'delete',
        params: { id }
    })
}

// 修改接口
const editClassification = (params) => {
    return $axios({
        url: '/alertClassification',
        method: 'put',
        data: { ...params }
    })
}

// 新增接口
const addClassification = (params) => {
    return $axios({
        url: '/alertClassification',
        method: 'post',
        data: { ...params }
    })
}