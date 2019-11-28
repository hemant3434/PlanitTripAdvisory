import React from 'react';
import { View, Text } from 'react-native';
import CardsContainer from '../sections/CardsContainer';
import CreateItinerary from '../navigation/CreateItinerary';
import axios from 'axios';
import LoadingPage from './LoadingScreen';
import * as constClass from '../constants/index';

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
    axios.get(constClass.CHECKITINERARY_EP)
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
    axios.get(constClass.VIEWITINERARY_EP)
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
