import React, { Component } from 'react';
import { StyleSheet, View } from 'react-native';
import ItineraryFilters from '../pages/ItineraryFilters';
import MapPicker from '../components/common/MapPicker/MapPicker';
import CardsContainer from '../sections/CardsContainer';
import ItineraryPage from '../pages/ItineraryPage';
import axios from 'axios';
import { awaitExpression } from '@babel/types';

const CREATE_ITINERARY = 'http://100.80.21.98:8080/api/v1/createItinerary'

export class CreateItinerary extends Component {
  async fetchData(){
    console.log("CREATE ITINERARY BODY!", this.state)
    axios.post(CREATE_ITINERARY, this.state)
    .then(response => console.log(response))
  }


  sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
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
        locationLat: null,
        locationLong: null,
        homeLat: null,
        homeLong: null,
        methodOfTrans: [],
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
        methodOfTrans: dataFromChild
      })
    }

    setActivities = (dataFromChild) => {
      this.setState ({
        activities: dataFromChild
      })
    }

    async setLocation(obj){
      console.log("OBJECT TEST", obj.latitude);
      this.state = {
        ...this.state,
        locationLat: obj.latitude,
        locationLong: obj.longitude,
        homeLat: obj.latitude,
        homeLong: obj.longitude,
      }
      this.fetchData();
      console.log("sleeping");
      await this.sleep(6000)
      console.log("done sleeping");
      this.setState({step: this.state.step + 1});
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
            this.state.methodOfTrans.length
          );
        case 2:
          return(
            this.state.locationLat &&
            this.state.locationLong
          );
        default:
          return true;
      }
    };

    nextStep = () => {
      const { step, date, startTime, endTime } = this.state;
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
              onLocationSelectProp={(obj)=>{this.setLocation(obj)
              console.log(obj.latitude);
              }}
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