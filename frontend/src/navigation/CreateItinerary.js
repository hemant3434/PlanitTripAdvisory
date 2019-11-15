import React, { Component } from 'react';
import { StyleSheet } from 'react-native';
import ItineraryFilters from '../pages/ItineraryFilters';
import MapPicker from '../components/common/MapPicker/MapPicker';
import CardsContainer from '../sections/CardsContainer';
import ItineraryPage from '../pages/ItineraryPage';
import axios from 'axios';

const CREATE_ITINERARY = 'http://100.80.11.91:8080/api/v1/createItinerary'

export class Multi extends Component {
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
    console.log(body)
    axios.post(CREATE_ITINERARY, body);
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
      activities: [
        "museums"
      ],
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

  setLocation = (latitude, longitude) => {
    const { step } = this.state;
    this.setState ({
      locationLatitude: latitude,
      locationLongitude: longitude,
      homeLatitude: latitude,
      homeLongitude: longitude,
      step: step + 1,
    })
    this.fetchData();
  }

  checkValues = () => {
    const { step } = this.state;
    switch(step) {
      case 1:
        return(
          this.state.date &&
          this.state.startTime &&
          this.state.endTime &&
          this.state.distance &&
          this.state.budget &&
          this.state.transportation.length
        );
      case 2:
        return(
          true
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
          />
        );
      case 2:
        return(
          <MapPicker
            onLocationSelectProp={(obj)=>this.setLocation(obj.latitude, obj.longitude)}
          />
        );
      case 3:
        return (
          <ItineraryPage />
      );
    }
  }
}

export default Multi;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
