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
    reqData = {
      startTime: qs.stringify("2019-10-03 2:48:41 PM"),
      endTime: qs.stringify("2019-10-03 2:48:41 PM"),
      maxDist: qs.stringify(20),
      budget: qs.stringify(500),
      locationLat: qs.stringify(43.76768768758),
      locationLong: qs.stringify(-43.76768768758),
      homeLat: qs.stringify(43.76768768758),
      homeLong: qs.stringify(-43.76768768758),
      methodOfTrans: qs.stringify(["Drive"]),
      activities: qs.stringify(["museums"])
    };
    console.log(reqData)
    axios.get('http://localhost:8080/api/v1/getItinerary',
    {
      params: reqData,
      paramsSerializer: params => {
        console.log(qs.stringify(reqData));
        return qs.stringify(params)
      }
    })
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
    this.nextStep();
  }

  checkValues = () => {
    const { step } = this.state;
    switch(step) {
      case 1:
        return(
          true
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
        console.log(this.state.date);
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
