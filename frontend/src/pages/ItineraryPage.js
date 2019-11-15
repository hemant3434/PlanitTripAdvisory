import React from 'react';
import { View } from 'react-native';
import CardsContainer from '../sections/CardsContainer';
import CreateItinerary from '../navigation/CreateItinerary';
import axios from 'axios';


export default class Itinerary extends React.Component {
  constructor(props){
    super(props)
    this.state = {
      Itinerary: null,
      ItineraryData: null
    }
  }

  componentDidMount(){
    this.fetch();
  }

  fetch(){
    axios.get("localhost/api/v1/getItinerary")
    .then(res => {
      console.log("res.data", res.data);
      if(res.data === null){
        this.setState({
          Itinerary: false
        })
      } else {
        this.setState({
          Itinerary: true,
          ItineraryData: res.data
        })
      }
    })
    .catch(e => console.log(e))
  }

  render(){

    if(this.state.Itinerary === null){
      return(
        <View>
          <Text>LOADING!</Text>
        </View>
      )
    }
    return(
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
        { this.state.Itinerary ? <CardsContainer common={this.state.ItineraryData}/> : <CreateItinerary/> }
      </View>
    );
  }
}
