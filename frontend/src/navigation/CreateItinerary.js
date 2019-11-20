import React, { Component } from 'react';
import { StyleSheet, View } from 'react-native';
import ItineraryFilters from '../pages/ItineraryFilters';
import MapPicker from '../components/common/MapPicker/MapPicker';
import CardsContainer from '../sections/CardsContainer';
import ItineraryPage from '../pages/ItineraryPage';
import axios from 'axios';

const CREATE_ITINERARY = 'http://localhost:8080/api/v1/createItinerary'

export class CreateItinerary extends Component {
  fetchData(){
    const body = {
      startTime: this.state.startTime,
      endTime: this.state.endTime,
      maxDist: this.state.distance,
      budget: this.state.budget,
      locationLat: this.state.locationLatitude,
      locationLong: this.state.locationLongitude,
      homeLat: this.state.homeLatitude,
      homeLong: this.state.homeLongitude,
      methodOfTrans: this.state.transportation,
      activities: this.state.activities
    }
    const tmp =
      {
	"startTime": "2019-10-03 2:47:41 PM",
	"endTime": "2019-10-03 8:48:41 PM",
	"maxDist": 20,
	"budget": 590,
	"locationLat": 43.76768768758,
	"locationLong": -789.3586968758798,
	"homeLat": -83.76768768758,
	"homeLong": 9.3586968758798,
	"methodsOfTrans": [
		"Drive",
		"Transit",
		"Ride Services"
	],
	"activities": [
		"Entertainment",
		"Food/Restaurants"
	]
}
    //console.log(body)
    axios.post(CREATE_ITINERARY, tmp);
  }


    constructor(props){
      super(props)
      this.state = {
        step: 1,
        date: null,
        startTime: null,
        endTime: null,
        budget: null,
        distance: null,
        locationLatitude: null,
        locationLongitude: null,
        homeLatitude: null,
        homeLongitude: null,
        transportation: [],
        activities: [],
        isLoading: true,
        Itinerary: null
      };
      this.nextStep = this.nextStep.bind(this);
    }

    setDate = (dataFromChild) => {
      this.setState({
        date: dataFromChild
      })
    }

    setStartTime = (dataFromChild) => {
      this.setState({
        startTime: dataFromChild
      })
    }

    setEndTime = (dataFromChild) => {
      this.setState({
        endTime: dataFromChild
      })
    }

    setBudget = (dataFromChild) => {
      this.setState({
        budget: dataFromChild
      })
    }

    setDistance = (dataFromChild) => {
      this.setState({
        distance: dataFromChild
      })
    }

    setTransportation = (dataFromChild) => {
      this.setState ({
        transportation: dataFromChild
      })
    }

    setActivities = (dataFromChild) => {
      this.setState ({
        activities: dataFromChild
      })
    }

    setLocation = (latitude, longitude) => {
      this.setState ({
        locationLatitude: latitude,
        locationLongitude: longitude,
        homeLatitude: latitude,
        homeLongitude: longitude,
      });
      this.nextStep()
      this.fetchData()
    }

    checkValues = () => {
      const { step } = this.state;
      switch(step) {
        case 1:
          return(
            true
            // this.state.date &&
            // this.state.startTime &&
            // this.state.endTime &&
            // this.state.distance &&
            // this.state.budget &&
            // this.state.transportation.length
          );
        case 2:
          return(
            true
            // this.state.locationLat &&
            // this.state.locationLong
          );
        default:
          return true;
      }
    };

    nextStep = () => {
      const { step, date, startTime, endTime } = this.state;
      console.log(this.state);
      if (this.checkValues()) {
        this.setState({
          startTime: date + " " + startTime,
          endTime: date + " " + endTime,
          step: step + 1
        });
      }
    };

    previousStep = () => {
      const { step } = this.state;
      this.setState({
        step: step - 1
      });
    };

    handleChange = input => e => {
      this.setState({ [input]: e.target.value });
    };

    render(){
      const { step } = this.state;
      switch(step){
        case 1:
          return(
            <ItineraryFilters
              continue={this.nextStep}
              previous={this.previousStep}
              setDateFromParent={this.setDate}
              setStartTimeFromParent={this.setStartTime}
              setEndTimeFromParent={this.setEndTime}
              setBudgetFromParent={this.setBudget}
              setDistanceFromParent={this.setDistance}
              setTransportationFromParent={this.setTransportation}
              setActivitiesFromParent={this.setActivities}
            />
          );
        case 2:
          return(
            <MapPicker
              onLocationSelectProp={(obj)=>this.setLocation(obj.latitude, obj.longitude)}
            />
          );
        case 3:
          console.log("Case 3");
          return (
            <ItineraryPage/>);
      }
    }
  }

  export default CreateItinerary;

  const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },
  });
