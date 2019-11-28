import React, { Component } from 'react';
import { Text, View } from 'react-native';
import { Card, Button, Icon } from 'react-native-elements';
import axios from 'axios';
import * as constClass from '../../../constants/index';

class ImageCard extends Component{
  constructor(props){
    super(props)
    this.state = {
      Itinerary: this.props.common
    }
  }

  delete(id){
    axios.put(constClass.DELETEEVENT_EP, { eventId: id })
    .then(res => {console.log(res.data)});
  }

  render(){
    console.log("EventCard", this.props);
    return (
      <Card
        title={this.props.title}
        image={this.props.image}>
        <Text style={{marginBottom: 10}}>{this.props.text}</Text>
        <Text>cost: {"$".repeat(this.props.cost)}</Text>
        <Text>People tyically spend {this.props.time.hour + " hours, " + this.props.time.minute + " minutes here"}</Text>
        <View style={{flexDirection: 'row', flex: 2, justifyContent: 'space-between'}}>
          <Button
        icon={<Icon name='code' color='#ffffff'/>}
        title='ADD EVENT'/>
        <Button
        icon={<Icon name='code' color='#ffffff'/>}
        title='DELETE EVENT'
        onPress={() => {this.delete(this.props.id)
                        this.props.update();}}/></View>
      </Card>
    );
  }
}

export default ImageCard;
