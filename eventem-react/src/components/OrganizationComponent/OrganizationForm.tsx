import React, { useState } from 'react';
import {API_ORGANIZATIONS_PATH} from '../../constants/apiConstants';
import axios from 'axios';
import { useAuth } from '../../auth/AuthContext';
import { OrganizationRequest } from '../../models/OrganizationRequest';
const OrganizationForm: React.FC = () => {
const [formData, setFormData] = useState<OrganizationRequest>({
name: '',
city: '',
address: '',
phone: '',
email: '',
});
const { token } = useAuth();
const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
        console.log(token);
        const response = await axios.post(API_ORGANIZATIONS_PATH, formData, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};
const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const {name, value} = e.target;
    setFormData({
        ...formData,
        [name]:value,
    });
};
return (<form onSubmit={handleSubmit}>
    <input type="text" placeholder="Name" name="name" value={formData.name} onChange={handleInputChange}/>
    <input type="text" placeholder="City" name="city" value={formData.city} onChange={handleInputChange}/>
    <input type="text" placeholder="Address" name="address" value={formData.address} onChange={handleInputChange}/>
    <input type="text" placeholder="Phone" name="phone" value={formData.phone} onChange={handleInputChange}/>
    <input type="text" placeholder="Email" name="email" value={formData.email} onChange={handleInputChange}/>
    <button type="submit">Add Organization</button>
    </form>);
    
};
export default OrganizationForm;