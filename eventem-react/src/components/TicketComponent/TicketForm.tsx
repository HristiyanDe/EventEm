import React, { useState } from 'react';
import { API_TICKETS_PATH } from '../../constants/apiConstants'; 
import axios from 'axios';
import { useAuth } from '../../auth/AuthContext';
import { TicketRequest } from '../../models/TicketRequest';
const TicketForm: React.FC = () => {
const [formData, setFormData] = useState<TicketRequest>({
    name: '',
    eventId: '',
    price: 0,
    });
const { token } = useAuth();
const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
        console.log(token);
        const response = await axios.post(API_TICKETS_PATH, formData, {
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
    <input type="text" placeholder="Event Id" name="eventId" value={formData.eventId} onChange={handleInputChange}/>
    <input type="text" placeholder="Price" name="price" value={formData.price} onChange={handleInputChange}/>
    <button type="submit">Add Ticket</button>
    </form>);
}
export default TicketForm;