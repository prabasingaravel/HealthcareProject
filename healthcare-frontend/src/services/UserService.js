import axios from "axios";

const USER_BASE_URL = "http://localhost:8081/users";

const BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQcmFiYSIsImV4cCI6MTYxNDc4NzAxMSwiaWF0IjoxNjE0NzUxMDExfQ.y85T5X-FzzqkMnGk5kuKhoR7FbMof6eYxnNG82-ZySo";

class UserService {

    getAllUser() {
        const CURRENT_URL = USER_BASE_URL + "/"
        return axios.get(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    addUser(users) {
        const CURRENT_URL = USER_BASE_URL + "/"
        return axios.post(CURRENT_URL, users, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    getUserById(id) {
        const CURRENT_URL = USER_BASE_URL + "/" + id;
        return axios.get(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    updateUser(users) {
        const CURRENT_URL = USER_BASE_URL + "/"
        return axios.put(CURRENT_URL, users, {
            headers: { 
                "Authorization": BEARER_TOKEN
             }
        });
    }

    deleteUser(id) {
        const CURRENT_URL = USER_BASE_URL + "/" + id;
        return axios.delete(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }
}

export default new UserService()