const buying = [
  {
    id: 1,
    picture: 'onion.png',
    title: '양파 필요하신분',
    food: '양파',
    address: '대전광역시 유성구 덕명동',
    date: '2020/08/01',
    members: '9/10'
  },
  {
    id: 2,
    picture: 'potato.png',
    title: '♡감자가 좋아♡',
    food: '감자',
    address: '대전광역시 유성구 덕명동',
    date: '2020/08/01',
    members: '4/5'
  },
  {
    id: 3,
    picture: 'onion.png',
    title: '양파 급하게 필요하신 분만!',
    food: '양파',
    address: '대전광역시 유성구 덕명동',
    date: '2020/07/16',
    members: '1/3'
  },
  {
    id: 4,
    picture: 'egg.png',
    title: '한밭대 달걀 공구',
    food: '달걀',
    address: '대전광역시 유성구 덕명동',
    date: '2020/08/20',
    members: '2/5'
  },
  {
    id: 5,
    picture: 'egg.png',
    title: '달걀 도매 구매',
    food: '달걀',
    address: '대전광역시 유성구 봉명동',
    date: '2020/08/31',
    members: '11/30'
  },
  {
    id: 6,
    picture: 'egg.png',
    title: '달걀 도매 구매',
    food: '달걀',
    address: '대전광역시 유성구 봉명동',
    date: '2020/08/31',
    members: '11/30'
  }
]

export default {
  fetch() {
    return buying
  },
  find(id) {
    return buying.find(el => el.id === id)
  },
  asyncFind(id, callback) {
    setTimeout(() => {
      callback(buying.find(el => el.id === id))
    }, 1000)
  }
}