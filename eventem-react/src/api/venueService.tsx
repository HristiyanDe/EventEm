import axios from "axios";
import { Venue } from "../models/dtos/VenueDTO";
import { API_VENUES_PATH } from "../constants/apiConstants";
class VenueService{
    async getVenuesFiltered(token: string,name?: string, address?: string, city?: string, ): Promise<Venue[]>{
        const params: Record<string, string> = {};
    
    if (name) params.venueName = name;
    if (city) params.venueCity = city;
    if (address) params.venueAddress = address;

        const response = await axios.get<Venue[]>(API_VENUES_PATH,{
            params,
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        return response.data;
    }
    async fetchVenues(token: string): Promise<Venue[]> {
        const response = await axios.get<Venue[]>(API_VENUES_PATH, {headers: {
            Authorization: `Bearer ${token}`,
        }});
        return response.data;
    }
}
export const venueService = new VenueService();