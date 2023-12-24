import React, { useState } from 'react';
import { API_EVENTS_PATH } from '../../constants/apiConstants';
import axios from 'axios';
import { useAuth } from '../../auth/AuthContext';
import { EventRequest } from '../../models/EventRequest';
import { EventStatusEnum } from '../../models/enums/EventStatusEnum';
const EventForm: React.FC = () => {
    const { token, setToken } = useAuth();
const [formData, setFormData] = useState<EventRequest>({
name: '',
eventStatus: EventStatusEnum.NONE,
startDate: '',
endDate: '',
venueId: 0,
organizationId: 0,
description: '',
maxAttendees: 0,
categories: [{
    categoryName: '',
}],
//TODO: make the user able to add multiple categories to an event
});
const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
        console.log(token);
        const response = await axios.post(API_EVENTS_PATH, formData, {
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
    const { name, value } = e.target;
  
    if (name.startsWith("categoryName")) {
      const categoryIndex = Number(name.split("_")[1]);
      const updatedCategories = [...formData.categories];
      updatedCategories[categoryIndex] = { categoryName: value };
      setFormData((prevFormData) => ({
        ...prevFormData,
        categories: updatedCategories,
      }));
    } else {
      setFormData((prevFormData) => ({
        ...prevFormData,
        [name]: value,
      }));
    }
  };
return (<form onSubmit={handleSubmit}>
    <input type="text" placeholder="Name" name="name" value={formData.name} onChange={handleInputChange}/>
    <input type="text" placeholder="Event Status" name="eventStatus" value={formData.eventStatus} onChange={handleInputChange}/>
    <input type="text" placeholder="Start Date" name="startDate" value={formData.startDate} onChange={handleInputChange}/>
    <input type="text" placeholder="End Date" name="endDate" value={formData.endDate} onChange={handleInputChange}/>
    <input type="text" placeholder="Venue ID" name="venueId" value={formData.venueId} onChange={handleInputChange}/>
    <input type="text" placeholder="Organization ID" name="organizationId" value={formData.organizationId} onChange={handleInputChange}/>
    <input type="text" placeholder="Description" name="description" value={formData.description} onChange={handleInputChange}/>
    <input type="text" placeholder="Max Attendees" name="maxAttendees" value={formData.maxAttendees} onChange={handleInputChange}/>
    {formData.categories.map((category, index) => (
      <input
        key={index}
        type="text"
        placeholder="Category Name"
        name={`categoryName_${index}`}
        value={category.categoryName}
        onChange={handleInputChange}
      />
    ))}
    <button type="submit">Add Event</button>
    </form>);
}
export default EventForm;