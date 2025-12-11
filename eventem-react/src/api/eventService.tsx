import axios from 'axios';
import { API_EVENTS_PATH } from '../constants/apiConstants';
import { FindEventRequest } from '../models/FindEventRequest';
class EventService {

    async searchEvents(name: string, token: string | null): Promise<FindEventRequest[]> {
    const response = await axios.get(`${API_EVENTS_PATH}/search`, {
        params: { name },
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
        },
    });
    console.log("Search Events Response: ", response.data);
    return response.data;
}
}
export const eventService = new EventService();