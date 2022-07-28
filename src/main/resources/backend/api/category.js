// 查询列表接口
const getCategoryPage = (params) => {
  return $axios({
    url: '/deviceType/page',
    method: 'get',
    params
  })
}

// 编辑页面反查详情接口
const queryCategoryById = (id) => {
  return $axios({
    url: `/deviceType/${id}`,
    method: 'get'
  })
}

// 删除当前列的接口
const deleteCategoryType = (id) => {
  return $axios({
    url: '/deviceType',
    method: 'delete',
    params: { id }
  })
}

// 修改接口
const editCategory = (params) => {
  return $axios({
    url: '/deviceType',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addCategory = (params) => {
  return $axios({
    url: '/deviceType',
    method: 'post',
    data: { ...params }
  })
}