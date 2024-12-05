# Parking manager-park 方法
假设你是一个资深的软件工程师，帮我生成一个 Parking manager 的类它有 3 个 parkinglot： 1、The Plaza Park 有 9 个空位 2、City Mall Garage 有 12 个空位 3、Office Tower Parking 有 9 个空位它还有 3 个不同 ParkingStrategy 的 parkingboy  它有一个方法 park 输入是 parkingStrategy 的类型（STANDARD SMART SUPER）和 plateNumber（String 类型） 会根据不同的 strategy 输入来选择不同的 parkingStrategy 输出是一个 Ticket

# 生成 park test
现在请为你刚刚生成的 parkingManager 中的 park 方法写一些单元测试，要求覆盖率尽可能高，且要考虑到异常情况，以 should_return_{}given{}when{}then{}的格式去写单元测试

# 生成 fetch 方法
现在为 parkingManager 新加一个方法，入参是一个 Ticket，返回是一个 Car，让 parkingManager 调用 ParkingBoy 中的方法

# Controller
现在请帮我生成一个 ParkingLotController 类，让我调用你在 ParkingManager 中生成的 park 与 fetch 方法，先帮我生成 4 个接口，分别是： 1、获取所有停车场的车位情况，返回一个 ResponseEntity，里面包裹着一个 List，list 里面的每个元素有停车场 id，停车场名字与这个停车场的剩余车位，get 请求，路径是/parkinglot 2、根据车号 plateNumber 和停车策略 parkingStrategy（STANDARD SMART SUPER）进行 park，返回一个 ResponseEntity，里面包裹着 Ticket，post 请求，路径是/pakringlot/park 3、根据 plateNumber 进行 fetch，返回一个 ResponseEntity，里面包裹 Car，post 请求，路径是/pakringlot/fetch



# 前端结构
假设你是一名资深的软件工程师，我正在开发一个前端的停车场页面，暂时先把页面划分成三个组件，一个大组件 ParkingLot 里面包含着两个小组件，一个组件是 ParkingLotOperator，一个组件是 ParkingLotSituation，先不要具体实现页面，把组件的结构构建好，并在 App. Js 中渲染 ParkingLot 大组件，所有组件使用 const xxx = （）的格式定义


# 用数组模拟停车场
现在我们在 ParkingLotSituation 中模拟一些数据， 1、假设我有三个停车场，他们分别叫 The Plaza Park（9 个容量），City Mall Garage（12 个容量），Office Tower Parking（9 个容量），为每一个停车场画一个表格来呈现停车场的停车情况，比如容量为 9 的停车场就画一个 33 的表格，容量为 12 的停车场就画一个 34 的表格，注意不要展现表格最外面的边框，但是内侧的边框要展现，且 3 个停车场要在同一行，并在每个停车场下方展示停车场的名称  2、使用 3 个数组来模拟每一个停车场的情况，比如 The plaza park 停了两辆车，就在第一个格子和第二个格子画 x，其他格子保证空白，City mall garage 停了三辆车，就在第一行的三个格子画 x，Office Tower Parking 9 个格子都是空白的


现在尝试把停车场中有车的情况的 x 换成一个组件，它的形状是有圆角的长方形，底色是 #b0f2b8 ，内容展示车牌号，可以调整模拟数据中数组的属性

# ParkingLotOperator
现在我们来实现 ParkingLotOperator，这个组件里面有几个元素， 
1、输入框，它的 label 是 Plate Number，这里用 setState 维持一个变量 plateNumber，当输入框的内容变化的时候把 plateNumber 更新 
2、下拉框，它的选项有 Standard，Smart，SuperSmart 这里用 setState 维持一个变量 parkingStrategy，当下拉框的内容变化的时候把 parkingStrategy 更新 
3、button，内容是 park，当按下按钮的时候打印日志，把 plateNumber 和 parkingStrategy 都打印出来
4、button，内容是 fetch，当按下按钮的时候打印日志 

4 个元素都在同一行，且每个元素之间都有一定的间距，元素之中的内容和元素本身也有一定的间距（使用 padding），且两个按钮的底色是 #a7d9fe

# Get  Car
创建一个 js 文件专门存放请求，baseUrl 是http://localhost:8080/parkinglot，并在 ParkingLot 组件渲染完毕时发一个 get 请求 (使用 Axios 和 then，catch，finally 的方式)，路径是http://localhost:8080/parkinglot，它返回的是所有停车场的数据，示例如下： [ { "id": 1, "name": "The Plaza Park", "tickets": [ { "plateNumber": "ABC 123", "position": 1, "parkingLot": 1 }, { "plateNumber": "ABC 124", "position": 2, "parkingLot": 1 }, { "plateNumber": "ABC 125", "position": 3, "parkingLot": 1 } ] }, { "id": 2, "name": "City Mall Garage", "tickets": [] }, { "id": 3, "name": "Office Tower Parking", "tickets": [] } ] 其中 name 是停车场的名字，tickets 是这个停车场的情况，plateNumber 是车牌号，用于渲染 Car 组件，position 是这个停车场的位置，也就是我们当前在 ParkingLotSituation 渲染的表格的位置，根据这个接口返回的数据来重构当前在 ParkingLotProvider 的数组，并在 ParkingLotSituation 中渲染数据, 使用 useContext 管理数组，并使用 useReducer 触发数据的变更

# Park
在 ParkingLotOperator 中，当用户输入 Plate Number 与 ParkingStrategy 后，点击 Park，会发送一个 post 请求，把这两个参数放在请求的 body 中传过去后端，路径是 baseUrl + /park，后端返回的数据结构如下： { "plateNumber": "ABC 123", "position" : 4, "parkingLot": 3 } plateNumber 是本次 park 的车牌号，parkingLot 是停车场的 id（我们这里定义 The Plaza Park 的 id 是 1，City Mall Garage 的 id 是 2，Office Tower Parking 的 id 是 3 且 id 都不会改变），这个返回例子说明 ABC 123 在 3 号停车场也就是 Office Tower Parking 的 4 号位（第二排第一个）停车了，需要在该停车场对应的位置渲染一个 Car 组件（不要再发一次 get 请求）

# Fetch
在 ParkingLotOperator 中，当用户输入 Plate Number, 点击 Fetch，会发送一个 post 请求，Plate Number 放在请求的 body 中传过去后端，路径是 baseUrl + /fetch，后端返回的数据结构如下： { "plateNumber": "ABC 123" } plateNumber 是本次 park 的车牌号，我们需要将这个车牌的车从我们的停车场中移除