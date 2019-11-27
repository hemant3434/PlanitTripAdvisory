import React from 'react';
import { View, Text } from 'react-native';
import CardsContainer from '../sections/CardsContainer';
import CreateItinerary from '../navigation/CreateItinerary';
import axios from 'axios';
import LoadingPage from './LoadingScreen';

const IP_CHECK_ITINERARY = "http://192.168.0.189:8080/api/v1/checkItinerary";
const IP_VIEW_ITINERARY = "http://192.168.0.189:8080/api/v1/viewItinerary";
const LH_CHECK_ITINERARY = "http://localhost:8080/api/v1/checkItinerary";
const LH_VIEW_ITINERARY = "http://localhost:8080/api/v1/viewItinerary";


export default class Itinerary extends React.Component {
  constructor(props){
    super(props)
    this.state = {
      Itinerary: null,
      loading: true,
      ItineraryData: null
    }
    this.updateItinerary = this.updateItinerary.bind(this);
  }

  componentDidMount(){
    this.fetch();
  }

  fetch(){
    axios.get(LH_CHECK_ITINERARY)
    .then(res => {
      console.log("CHECK_ITINERARY.data", res.data);
      this.setState({
        Itinerary: res.data
      })
      if(res.data){
        this.fetchItinerary();
      }
    })
    .catch(e => console.log(e))
  }

  fetchItinerary(){
    console.log("FETCHING NOW");
    axios.get(LH_VIEW_ITINERARY)
    .then(res => {
      console.log("FETCHED ITINERARY", res.data);
      this.setState({
        ItineraryData: res.data,
        loading: false
      });
     })
    .catch(e => console.log(e))
  }

  updateItinerary(){
    console.log("function called in ItineraryPage");
    this.fetchItinerary();
    this.forceUpdate();
  }


  render(){
    switch(this.state.Itinerary){
      case null:
        return (
          <LoadingPage />
        );
      case true:
        while(this.state.loading){
          return (
            <LoadingPage />
          );
        }
        console.log("RENDER STATE", this.state);
        return <CardsContainer common={this.state.ItineraryData} update={this.updateItinerary}/>
      default:
       return <CreateItinerary/>
    }
  }
}
