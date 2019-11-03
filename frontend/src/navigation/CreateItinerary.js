import React, { Component } from 'react';
import { Text, View, Button } from 'react-native';
import DateTime from '../pages/DateTime';
import ItineraryFilters from '../pages/ItineraryFilters';
import MapPicker from '../components/common/MapPicker/MapPicker';
import axios from 'axios';

export class Multi extends Component {
  componentDidMount() {
    this.fetchData();
  }

  fetchData(){
    axios.get('http://100.80.11.91:8080/api/v1/getItinerary', {
      date: this.state.date,
      startTime: this.state.startTime,
      endTime: this.state.endTime,
      location: this.state.location,
      distance: this.state.distance,
      budget: this.state.budget
    })
    .then(res => {
      this.setState({
        isLoading: false,
      });
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
      latitude: null,
      longitude: null,
      transportation: [],
      isLoading: true
    };
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
    this.setState ({
      latitude: latitude,
      longitude: longitude
    })
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
          this.state.latitude &&
          this.state.longitude
        );
    }
  };

  nextStep = () => {
    const { step, date, startTime, endTime } = this.state;
    console.log(this.checkValues());
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
        console.log(this.state);
        return(
          <MapPicker
            onLocationSelectProp={(obj)=>this.setLocation(obj.latitude, obj.longitude)}
          />
        );
    }
  }
}

export default Multi;
