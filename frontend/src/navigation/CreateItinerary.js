import React, { Component } from 'react';
import { ScrollView, Text, View, Button, StyleSheet } from 'react-native';
import DateTime from '../pages/DateTime';
import ItineraryFilters from '../pages/ItineraryFilters';
import MapPicker from '../components/common/MapPicker/MapPicker';
import EventCard from '../components/common/Cards/EventCard';
import CardsContainer from '../sections/CardsContainer';
import axios from 'axios';
import qs from 'qs';

export class Multi extends Component {
  componentDidMount() {
    this.fetchData();
  }

  fetchData(){
    var self = this;
    console.log(self.state.locationLat);
    reqData = {
      startTime: self.state.startTime,
      endTime: self.state.endTime,
      maxDist: self.state.distance,
      budget: self.state.budget,
      locationLat: self.state.locationLatitude,
      locationLong: self.state.locationLongitude,
      homeLat: self.state.homeLatitude,
      homeLong: self.state.homeLongitude,
      methodOfTrans: self.state.transportation,
      activities: self.state.activities
    };
    console.log(reqData);
    axios.get('http://localhost:8080/api/v1/getItinerary', reqData)
    .then(res => {
      console.log("res", res);
      const data = res.data;
      console.log("res.data", data);
      this.setState({
        isLoading: false,
        Itinerary: data
      });
      console.log(Itinerary);
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
    const { date } = this.state;
    this.setState({
      startTime: date + " " + dataFromChild
    })
  }

  setEndTime = (dataFromChild) => {
    const { date } = this.state;
    this.setState({
      endTime: date + " " + dataFromChild
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
    });
    this.fetchData();
    this.nextStep();
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
          this.state.locationLatitude &&
          this.state.locationLongitude
        );
      default:
        return true;
    }
  };

  nextStep = () => {
    const { step } = this.state;
    if (this.checkValues()) {
      this.setState({
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
          <ScrollView
          style={StyleSheet.absoluteFill}
          contentContainerStyle={styles.scrollview}>
          { !this.state.isLoading ? this.state.Itinerary.map(o => <EventCard common={o}/>):<Text>Loading</Text> }
          </ScrollView>
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
