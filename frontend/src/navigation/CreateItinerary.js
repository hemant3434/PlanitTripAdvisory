import React, { Component } from 'react';
import { ScrollView, Text, View, Button, StyleSheet } from 'react-native';
import DateTime from '../pages/DateTime';
import ItineraryFilters from '../pages/ItineraryFilters';
import MapPicker from '../components/common/MapPicker/MapPicker';
import EventCard from '../components/common/Cards/EventCard';
import CardsContainer from '../sections/CardsContainer';
import axios from 'axios';

export class Multi extends Component {

  fetchData(){
    axios.get('http://localhost:8080/api/v1/viewItinerary')
    .then(res => {
      const data = res.data;
      console.log(res.data.length==0);
      if(res.data.length==0) { // No itinerary exists
        const body = {
          activities: this.state.activities,
          budget: this.state.budget,
          endTime: this.state.endTime,
          homeLat: this.state.homeLatitude,
          homeLong: this.state.homeLongitude,
          locationLat: this.state.locationLatitude,
          locationLong: this.state.locationLongitude,
          maxDist: this.state.distance,
          methodOfTrans: this.state.transportation,
          startTime: this.state.startTime
        }
        console.log(body);
        axios.post('http://localhost:8080/api/v1/createItinerary', body)
        .then(res => {
          console.log("res", res);
          const data = res.data;
          this.setState({
            isLoading: false,
            Itinerary: data,
            step: 3
          });
        })
        .catch(error => console.log(error));;
      }
    })
    .catch(error => console.log(error));;
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
    this.nextStep(),
    this.fetchData()
  }

  checkValues = () => {
    const { step } = this.state;
    switch(step) {
      case 1:
        return(
          //true
          this.state.date &&
          this.state.startTime &&
          this.state.endTime &&
          this.state.distance &&
          this.state.budget &&
          this.state.transportation.length
        );
      case 2:
        return(
          //true
          this.state.locationLat &&
          this.state.locationLong
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
        return (
          <ScrollView
          style={StyleSheet.absoluteFill}
          contentContainerStyle={styles.scrollview}>
          { !this.state.isLoading ? this.state.Itinerary.map(o => <EventCard common={o}/>):<Text>Loading</Text> }
          </ScrollView>      );
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
