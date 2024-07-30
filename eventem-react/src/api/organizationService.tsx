import axios from "axios";
import { Organization } from "../models/dtos/OrganizationDTO";
import { API_ORGANIZATIONS_PATH, API_USER_ORGANIZATIONS_PATH } from "../constants/apiConstants";
import { OrganizationRequest } from "../models/OrganizationRequest";

class OrganizationService{
    async getUserOrganizations(userId: number, token: String | null): Promise<Organization[]>{
        const response = await axios.get<Organization[]>(API_USER_ORGANIZATIONS_PATH(userId),{
            headers: {
                Authorization: `Bearer ${token}`,
        },
    });
        return response.data;
    }

async createOrganizationComponent(token: string | null, formData: OrganizationRequest){
    if(!token){
        return null;
    }
    const response = await axios.post(API_ORGANIZATIONS_PATH, formData, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    return response.headers['location'];
}
}
export const organizationService = new OrganizationService();