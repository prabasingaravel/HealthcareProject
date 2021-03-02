import axios from 'axios';

const AUDIT_API_BASE_URL = "http://localhost:8084/audits/";

class AuditService{

    getAudits(){
        return axios.get(AUDIT_API_BASE_URL);
    }
    
}

export default new AuditService()